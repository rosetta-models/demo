package com.regnosys;

import com.google.common.base.CaseFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ExampleGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleGenerator.class);

    public static final String EXPECTATION_JSON_TEMPLATE = "expectation.json.template";
    public static final String INGESTIONS_JSON_TEMPLATE = "ingestion.json.template";
    public static final String UNIT_TEST_JAVA_TEMPLATE = "ingestion-test.java.template";

    public static final String $UNIT_TEST_NAME$ = "$UNIT_TEST_NAME$";
    public static final String $TEST_ANNOTATIONS$ = "$TEST_ANNOTATIONS$";
    public static final String $NAMESPACE$ = "$NAMESPACE$";
    public static final String $CATEGORY_NAME$ = "$CATEGORY_NAME$";
    public static final String $TEST_NAME$ = "$TEST_NAME$";
    public static final String $TEST_SAMPLE_NAME$ = "$TEST_SAMPLE_NAME$";
    public static final String $SYNONYM_NAME$ = "$SYNONYM_NAME$";
    public static final String $ROOT_TYPE$ = "$ROOT_TYPE$";
    public static final String $DESCRIPTION$ = "$DESCRIPTION$";
    public static final String $SCHEMA$ = "$SCHEMA$";

    public static final String ROOT_TYPE = "Root";
    public static final String SETTINGS_PROPERTIES_FILE_NAME = "settings.properties";
    public static final String TEST_ANNOTATIONS_SETTING_NAME = "testAnnotations";

    private final Path mainResourcesPath;
    private final Path testJavaPath;

    private final String expectationsTemplate;
    private final String ingestionTemplate;
    private final String unitTestTemplate;

    public ExampleGenerator(Path mainPath, Path testPath) throws IOException {
        this.mainResourcesPath = mainPath.resolve("resources");
        this.testJavaPath = testPath.resolve("java");

        Path templatePath = testPath
                .resolve("resources")
                .resolve("example-generation")
                .resolve("templates");
        expectationsTemplate = Files.readString(templatePath.resolve(EXPECTATION_JSON_TEMPLATE));
        ingestionTemplate = Files.readString(templatePath.resolve(INGESTIONS_JSON_TEMPLATE));
        unitTestTemplate = Files.readString(templatePath.resolve(UNIT_TEST_JAVA_TEMPLATE));
    }

    public List<ExampleSet> inputExampleSets(Path exampleGenerationInputsPath) throws IOException {
        List<ExampleSet> exampleSets = new ArrayList<>();

        List<Path> categories = Files.list(exampleGenerationInputsPath)
                .filter(Files::isDirectory)
                .collect(Collectors.toList());

        for (Path category : categories) {
            String categoryName = category.getFileName().toString();
            List<Path> tests = Files.list(category).filter(Files::isDirectory).collect(Collectors.toList());
            for (Path test : tests) {
                String testName = test.getFileName().toString();
                Path mdPath = path(".md", "Only one md file can be in ", test);
                Path rosettaPath = path(".rosetta", "Only one rosetta file can be in ", test);
                Path schemaPath = path(".xsd", "Only one xsd file can be in ", test);
                List<Path> xmlPath = Files.list(test).filter(Files::isRegularFile)
                        .filter(x -> x.toString().endsWith(".xml"))
                        .collect(Collectors.toList());

                if (ignoreExampleSet(rosettaPath)) {
                    LOGGER.warn("Ignoring example set {}", category);
                    continue;
                }

                Properties settings = new Properties();
                Path settingsFilePath = test.resolve(SETTINGS_PROPERTIES_FILE_NAME);
                if (Files.exists(settingsFilePath)) {
                    settings.load(new FileInputStream(settingsFilePath.toFile()));
                }

                exampleSets.add(new ExampleSet(categoryName, testName, mdPath, rosettaPath, schemaPath, xmlPath, settings));
            }
        }
        return exampleSets;
    }

    private boolean ignoreExampleSet(Path rosettaPath) {
//        try {
//            return Files.readString(rosettaPath).contains("// add content here");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return true;
//        }
        return false;
    }

    private Path path(String suffix, String msg, Path test) throws IOException {
        return Files.list(test)
                .filter(Files::isRegularFile)
                .filter(y -> y.toString().endsWith(suffix))
                .findFirst().orElseThrow(() -> new IllegalStateException(msg + test.toAbsolutePath()));
    }

    void writeSchema(ExampleSet exampleSet) throws IOException {
        Path schemas = Files.createDirectories(mainResourcesPath.resolve("schemas").resolve(exampleSet.categoryName));
        Files.copy(exampleSet.xsdFile,
                schemas.resolve(exampleSet.xsdFile.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
    }

    void writeXmlSamples(ExampleSet exampleSet) throws IOException {
        Path exampleXmlPath = Files.createDirectories(mainResourcesPath
                .resolve("cdm-sample-files")
                .resolve(exampleSet.categoryName)
                .resolve(exampleSet.testName));

        for (Path xmlFile : exampleSet.xmlFiles) {
            Files.copy(xmlFile,
                    exampleXmlPath.resolve(xmlFile.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
        }

        Path expectationsPath = exampleXmlPath.resolve("expectations.json");

        String expectations = exampleSet.xmlFiles.stream()
                .map(xmlSample ->
                        expectationsTemplate
                                .replace($CATEGORY_NAME$, exampleSet.categoryName)
                                .replace($TEST_NAME$, exampleSet.testName)
                                .replace($TEST_SAMPLE_NAME$, xmlSample.getFileName().toString())
                )
                .collect(Collectors.joining(",\n", "[", "]"));

        Files.write(expectationsPath, expectations.getBytes());
    }

    void writeIngestionJson(ExampleSet exampleSet) throws IOException {
        Path ingestions = Files.createDirectories(mainResourcesPath.resolve("ingestions"));
        Path ingestionPath = ingestions
                .resolve(exampleSet.categoryName + "-" + exampleSet.testName + "-ingestions.json");

        String ingestionsJson = ingestionTemplate
                .replace($NAMESPACE$, exampleSet.getNamespace())
                .replace($CATEGORY_NAME$, exampleSet.categoryName)
                .replace($TEST_NAME$, exampleSet.testName)
                .replace($SYNONYM_NAME$, exampleSet.getSynonymName())
                .replace($ROOT_TYPE$, "Root");

        Files.write(ingestionPath, ingestionsJson.getBytes());
    }

    void writeRosetta(ExampleSet exampleSet) throws IOException {
        String description = Files.readString(exampleSet.mdFile);
        String schema = Files.readString(exampleSet.xsdFile);
        String rosetta =
                Files.readString(exampleSet.rosettaFile)
                        .replace($DESCRIPTION$, description)
                        .replace($SCHEMA$, schema);

        Path rosettaPath = mainResourcesPath.getParent().resolve("rosetta")
                .resolve(exampleSet.categoryName.replace("-", "_")
                        + "-" + exampleSet.rosettaFile.getFileName().toString().replace("-", "_"));

        Files.write(rosettaPath, rosetta.getBytes());
    }

    void writeUnitTest(ExampleSet exampleSet) throws IOException {
        Path unitTestBasePath = Files.createDirectories(testJavaPath.resolve("demo").resolve("translate"));
        Path unitTestPath = unitTestBasePath.resolve(exampleSet.getUnitTestName() + ".java");

        String testAnnotations = exampleSet.settings.getProperty(TEST_ANNOTATIONS_SETTING_NAME, "");
        String unitTestJava = unitTestTemplate
                .replace($TEST_ANNOTATIONS$, testAnnotations)
                .replace($UNIT_TEST_NAME$, exampleSet.getUnitTestName())
                .replace($NAMESPACE$, exampleSet.getNamespace())
                .replace($CATEGORY_NAME$, exampleSet.categoryName)
                .replace($TEST_NAME$, exampleSet.testName)
                .replace($SYNONYM_NAME$, exampleSet.getSynonymName())
                .replace($ROOT_TYPE$, "Root");

        Files.write(unitTestPath, unitTestJava.getBytes());
    }

    public static void main(String[] args) throws IOException {
        Path mainPath = Path.of("../rosetta-source/src/main");
        Path testPath = Path.of("src/test");

        ExampleGenerator exampleGenerator = new ExampleGenerator(mainPath, testPath);

        Path exampleGenerationInputsPath = testPath
                .resolve("resources")
                .resolve("example-generation/inputs");

        List<ExampleSet> inputSets = exampleGenerator.inputExampleSets(exampleGenerationInputsPath);
        for (ExampleSet inputSet : inputSets) {
            exampleGenerator.writeSchema(inputSet);
            exampleGenerator.writeIngestionJson(inputSet);
            exampleGenerator.writeRosetta(inputSet);
            exampleGenerator.writeXmlSamples(inputSet);
            exampleGenerator.writeUnitTest(inputSet);
        }
    }

    public static String getNamespace(String categoryName, String exampleName) {
        return ("demo.translate." + categoryName + "." + exampleName)
                .toLowerCase()
                .replace("-", "_");
    }

    public static String getSynonymName(String categoryName, String exampleName) {
        return (categoryName + "_" + exampleName)
                .toUpperCase()
                .replace("-", "_");
    }

    static class ExampleSet {
        private final String categoryName;
        private final String testName;
        private final Path mdFile;
        private final Path rosettaFile;
        private final Path xsdFile;
        private final List<Path> xmlFiles;
        private final Properties settings;


        public ExampleSet(String categoryName, String testName, Path mdFile, Path rosettaFile, Path xsdFile, List<Path> xmlFiles, Properties settings) {
            this.categoryName = categoryName;
            this.testName = testName;
            this.mdFile = mdFile;
            this.rosettaFile = rosettaFile;
            this.xsdFile = xsdFile;
            this.xmlFiles = xmlFiles;
            this.settings = settings;
        }

        public String getSynonymName() {
            return ExampleGenerator.getSynonymName(categoryName, testName);
        }

        public String getNamespace() {
            return ExampleGenerator.getNamespace(categoryName, testName);
        }

        public String getUnitTestName() {
            return (CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, categoryName)
                    + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, testName)
                    + "IngestionTest");
        }
    }
}
