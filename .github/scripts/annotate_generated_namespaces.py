#!/usr/bin/env python3
"""Annotate a pull request with the generated Rune namespaces it modifies.

A namespace is *generated* when its ``namespaceConfig`` entry in rune-config.yml
carries an ``origin`` marker naming the tool that produced it, e.g.::

    namespaceConfig:
    - namespace: demo.unavista.csv
      origin:
        modelImport: csv

Unlike read-only namespaces, generated namespaces are meant to be refined by
hand -- completing enumerations, narrowing type aliases, adding documentation.
So this check never fails. It reports, so that the edit is visible in review.

Edits that touch the column binding -- a ``[label]``, an attribute name, or an
attribute type -- are raised to warning level, because those are the changes
that can break ingestion. Everything else is a notice.
"""

import argparse
import os
import re
import subprocess
import sys

NAMESPACE_RE = re.compile(r'^\s*(?:override\s+)?namespace\s+"?([A-Za-z0-9_.]+)"?', re.MULTILINE)
ATTRIBUTE_RE = re.compile(r'^[a-z][A-Za-z0-9_]*\s+[A-Za-z][A-Za-z0-9_]*\s*\(\s*\d')
HUNK_RE = re.compile(r'^@@ -\d+(?:,\d+)? \+(\d+)')

# GitHub renders at most ~10 annotations per level per step.
MAX_INLINE_ANNOTATIONS = 10


def git(*args):
    result = subprocess.run(["git", *args], capture_output=True, text=True, check=False)
    return result.stdout


def parse_generated_namespaces(text):
    """Return [(namespace_pattern, tool, value)] for entries carrying an `origin` marker."""
    block_match = re.search(
        r'^namespaceConfig\s*:\s*\n(.*?)(?=^[A-Za-z_]|\Z)', text, re.MULTILINE | re.DOTALL
    )
    if not block_match:
        return []
    entries = re.split(r'^\s*-\s', block_match.group(1), flags=re.MULTILINE)[1:]
    generated = []
    for entry in entries:
        namespace = re.search(r'namespace\s*:\s*"?([A-Za-z0-9_.*]+)"?', entry)
        origin = re.search(r'origin\s*:\s*\n\s+([A-Za-z0-9_]+)\s*:\s*([A-Za-z0-9_.-]+)', entry)
        if namespace and origin:
            generated.append((namespace.group(1), origin.group(1), origin.group(2)))
    return generated


def matches(namespace, pattern):
    """Namespace pattern matching, aligned with the Java implementation.

    A bare pattern is an exact match. A terminal `.*` covers the namespace and
    all of its subnamespaces, and is prefix-inclusive: `foo.bar.*` matches
    `foo.bar` itself. Matching is segment-aware, so `foo.bar.*` never matches
    `foo.barbaz`.
    """
    if pattern.endswith(".*"):
        prefix = pattern[:-2]
        return namespace == prefix or namespace.startswith(prefix + ".")
    return namespace == pattern


def namespace_of(path):
    try:
        with open(path, encoding="utf-8") as handle:
            match = NAMESPACE_RE.search(handle.read())
    except OSError:
        return None
    return match.group(1) if match else None


def changed_rosetta_files(base):
    output = git("diff", "--name-only", f"{base}...HEAD", "--", "*.rosetta")
    return [line for line in output.splitlines() if line.strip()]


def declaration_of(body):
    """The part of an attribute line that defines the binding, ignoring documentation."""
    return body.split('<"')[0].strip()


def classify(base, path):
    """Return (level, reason, line) for the change made to `path`."""
    diff = git("diff", "-U0", f"{base}...HEAD", "--", path)
    line_number = 1
    first_changed_line = None
    labels_changed = False
    added_declarations = set()
    removed_declarations = set()
    for line in diff.splitlines():
        hunk = HUNK_RE.match(line)
        if hunk:
            line_number = int(hunk.group(1))
            continue
        if line.startswith(("+++", "---")):
            continue
        if line.startswith(("+", "-")):
            body = line[1:].strip()
            if first_changed_line is None:
                first_changed_line = line_number
            if body.startswith("[label"):
                labels_changed = True
            elif ATTRIBUTE_RE.match(body):
                target = added_declarations if line.startswith("+") else removed_declarations
                target.add(declaration_of(body))
            if line.startswith("+"):
                line_number += 1
    if labels_changed:
        return "warning", "a column label", first_changed_line or 1
    if added_declarations != removed_declarations:
        return "warning", "an attribute name or type", first_changed_line or 1
    return "notice", None, first_changed_line or 1


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--base", required=True, help="Git ref to diff HEAD against.")
    parser.add_argument("--config", required=True, help="Path to rune-config.yml.")
    parser.add_argument("--root", default=".", help="Directory under which .rosetta files live.")
    args = parser.parse_args()

    try:
        with open(args.config, encoding="utf-8") as handle:
            generated = parse_generated_namespaces(handle.read())
    except OSError as error:
        print(f"::notice::Could not read {args.config}: {error}")
        return 0

    if not generated:
        return 0

    findings = []
    for path in changed_rosetta_files(args.base):
        if not path.startswith(args.root.rstrip("/")) or not os.path.exists(path):
            continue
        namespace = namespace_of(path)
        if namespace is None:
            continue
        for pattern, tool, value in generated:
            if matches(namespace, pattern):
                level, reason, line = classify(args.base, path)
                findings.append((level, path, line, namespace, tool, value, reason))
                break

    if not findings:
        return 0

    findings.sort(key=lambda f: (f[0] != "warning", f[1]))

    for level, path, line, namespace, tool, value, reason in findings[:MAX_INLINE_ANNOTATIONS]:
        if reason:
            message = (
                f"Namespace '{namespace}' is maintained by {tool} ({value}). "
                f"This change edits {reason}, which the CSV column binding depends on - "
                f"confirm that ingestion still resolves."
            )
            title = "Generated namespace: binding changed"
        else:
            message = (
                f"Namespace '{namespace}' is maintained by {tool} ({value}). "
                f"Hand edits are expected here; this note exists so the change is visible in review."
            )
            title = "Generated namespace modified"
        print(f"::{level} file={path},line={line},title={title}::{message}")

    summary_path = os.environ.get("GITHUB_STEP_SUMMARY")
    if summary_path:
        with open(summary_path, "a", encoding="utf-8") as handle:
            handle.write("### Generated namespaces modified by this pull request\n\n")
            handle.write("| | File | Namespace | Origin | Change |\n")
            handle.write("|---|---|---|---|---|\n")
            for level, path, _line, namespace, tool, value, reason in findings:
                icon = "warning" if level == "warning" else "note"
                handle.write(
                    f"| {icon} | `{path}` | `{namespace}` | {tool} ({value}) | "
                    f"{reason or 'documentation or model detail'} |\n"
                )
            handle.write(
                "\nThese namespaces were produced by an importer. Editing them is expected - "
                "this check reports rather than blocks, so that the change is visible in review.\n"
            )

    return 0


if __name__ == "__main__":
    sys.exit(main())
