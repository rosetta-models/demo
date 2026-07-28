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

Classification is deliberately **fail-safe** and carries almost no knowledge of
Rune syntax, so that it stays correct as the language evolves. Rather than
recognising a list of risky constructs -- which would silently under-report any
construct it had not been taught -- it recognises only what is demonstrably
harmless and treats everything else as worth a reviewer's attention:

* a change that disappears once documentation and comments are stripped is a
  **notice**;
* a change that only adds declarations is a **notice**, since data that parsed
  before still parses;
* anything that modifies or removes a declaration is a **warning**, because a
  renamed label, a narrowed alias or a changed type can break ingestion.

An unrecognised construct therefore lands in the warning bucket rather than
being missed. One annotation is emitted per file.
"""

import argparse
import os
import re
import subprocess
import sys

NAMESPACE_RE = re.compile(r'^\s*(?:override\s+)?namespace\s+"?([A-Za-z0-9_.]+)"?', re.MULTILINE)
DOC_STRING_RE = re.compile(r'<"(?:[^"\\]|\\.)*">')
LINE_COMMENT_RE = re.compile(r'//.*$')

# GitHub renders at most ~10 annotations per level per step.
MAX_INLINE_ANNOTATIONS = 10

# Every annotation ends with this, so a reviewer who has not seen one before can
# find out what generated namespaces are and why the note is there.
DEFAULT_DOCS_URL = "https://rune.finos.org/docs/developers/generated-namespaces"


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


def without_documentation(body):
    """A line reduced to its declaration, with documentation and comments removed."""
    body = DOC_STRING_RE.sub("", body)
    body = LINE_COMMENT_RE.sub("", body)
    return " ".join(body.split())


def classify(base, path):
    """Return (level, description) for the change made to `path`.

    Errs towards `warning`: only changes that are provably documentation-only or
    purely additive are downgraded to a notice.
    """
    diff = git("diff", "-U0", f"{base}...HEAD", "--", path)
    added, removed = [], []
    for line in diff.splitlines():
        if line.startswith(("+++", "---", "@@")):
            continue
        if line.startswith("+"):
            added.append(line[1:].strip())
        elif line.startswith("-"):
            removed.append(line[1:].strip())

    declarations_added = sorted(filter(None, (without_documentation(b) for b in added)))
    declarations_removed = sorted(filter(None, (without_documentation(b) for b in removed)))

    if declarations_added == declarations_removed:
        return "notice", "changed documentation or comments only"
    if not declarations_removed:
        return "notice", "added declarations, without changing or removing any"
    return "warning", "changed or removed existing declarations"


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--base", required=True, help="Git ref to diff HEAD against.")
    parser.add_argument("--config", required=True, help="Path to rune-config.yml.")
    parser.add_argument("--root", default=".", help="Directory under which .rosetta files live.")
    parser.add_argument(
        "--docs-url",
        default=DEFAULT_DOCS_URL,
        help="Documentation link appended to every annotation.",
    )
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
                level, description = classify(args.base, path)
                findings.append((level, path, namespace, tool, value, description))
                break

    if not findings:
        return 0

    findings.sort(key=lambda finding: (finding[0] != "warning", finding[1]))

    for level, path, namespace, tool, value, description in findings[:MAX_INLINE_ANNOTATIONS]:
        if level == "warning":
            title = "Generated namespace: declarations changed"
            advice = (
                "If this touches a label, an attribute name or an attribute type, the CSV "
                "column binding changes with it - confirm that ingestion still resolves."
            )
        else:
            title = "Generated namespace modified"
            advice = (
                "Hand edits are expected here; this note exists so the change is visible in review."
            )
        # One annotation per file, anchored at the top of the file rather than at a
        # particular construct, so that nothing depends on recognising Rune syntax.
        print(
            f"::{level} file={path},line=1,title={title}::"
            f"Namespace '{namespace}' is maintained by {tool} ({value}). "
            f"This pull request has {description}. {advice} "
            f"What is this? {args.docs_url}"
        )

    summary_path = os.environ.get("GITHUB_STEP_SUMMARY")
    if summary_path:
        with open(summary_path, "a", encoding="utf-8") as handle:
            handle.write("### Generated namespaces modified by this pull request\n\n")
            handle.write("| | File | Namespace | Origin | Change |\n")
            handle.write("|---|---|---|---|---|\n")
            for level, path, namespace, tool, value, description in findings:
                icon = "warning" if level == "warning" else "note"
                handle.write(
                    f"| {icon} | `{path}` | `{namespace}` | {tool} ({value}) | {description} |\n"
                )
            handle.write(
                "\nThese namespaces were produced by an importer. Editing them is expected - "
                "this check reports rather than blocks, so that the change is visible in review.\n"
                f"\n[What are generated namespaces?]({args.docs_url})\n"
            )

    return 0


if __name__ == "__main__":
    sys.exit(main())
