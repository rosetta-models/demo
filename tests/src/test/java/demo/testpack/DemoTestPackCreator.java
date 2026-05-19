package demo.testpack;

import com.google.common.base.Stopwatch;
import com.google.inject.Injector;
import demo.emissions.DemoTestRuntimeModule;
import demo.ingest_synonym.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoTestPackCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoTestPackCreator.class);

    @Inject
    private ConditionalDefaultExample1IngestionTest conditionalDefaultExample1Ingestion;
    @Inject
    private ConditionalSetExample1IngestionTest conditionalSetExample1Ingestion;
    @Inject
    private ConditionalSetExample2IngestionTest conditionalSetExample2Ingestion;
    @Inject
    private ConditionalSetExample3IngestionTest conditionalSetExample3Ingestion;
    @Inject
    private ConditionalSetExample4IngestionTest conditionalSetExample4Ingestion;
    @Inject
    private ConditionalSetExample5IngestionTest conditionalSetExample5Ingestion;
    @Inject
    private ConditionalSetExample6IngestionTest conditionalSetExample6Ingestion;
    @Inject
    private ConditionalSetExample7IngestionTest conditionalSetExample7Ingestion;
    @Inject
    private ConditionalSetExample8IngestionTest conditionalSetExample8Ingestion;
    @Inject
    private ConditionalSetExample9IngestionTest conditionalSetExample9Ingestion;
    @Inject
    private ConditionalSetExample10IngestionTest conditionalSetExample10Ingestion;
    @Inject
    private ConditionalSetExample11IngestionTest conditionalSetExample11Ingestion;
    @Inject
    private ConditionalSetExample12IngestionTest conditionalSetExample12Ingestion;
    @Inject
    private ConditionalSetExample13IngestionTest conditionalSetExample13Ingestion;
    @Inject
    private ConditionalSetExample14IngestionTest conditionalSetExample14Ingestion;
    @Inject
    private ConditionalSetExample15IngestionTest conditionalSetExample15Ingestion;
    @Inject
    private ConditionalSetExample16IngestionTest conditionalSetExample16Ingestion;
    @Inject
    private ConditionalSetExample17IngestionTest conditionalSetExample17Ingestion;
    @Inject
    private ConditionalSetExample18IngestionTest conditionalSetExample18Ingestion;
    @Inject
    private ConditionalSetExample19IngestionTest conditionalSetExample19Ingestion;
    @Inject
    private ConditionalSetExample20IngestionTest conditionalSetExample20Ingestion;
    @Inject
    private ConditionalSetExample21IngestionTest conditionalSetExample21Ingestion;
    @Inject
    private ConditionalSetExample22IngestionTest conditionalSetExample22Ingestion;
    @Inject
    private ConditionalSetExample23IngestionTest conditionalSetExample23Ingestion;
    @Inject
    private ConditionalSetExample24IngestionTest conditionalSetExample24Ingestion;
    @Inject
    private ConditionalSetExample25IngestionTest conditionalSetExample25Ingestion;
    @Inject
    private ConditionalSetExample26IngestionTest conditionalSetExample26Ingestion;
    @Inject
    private ConditionalSetToExample1IngestionTest conditionalSetToExample1Ingestion;
    @Inject
    private ConditionalSetToExample2IngestionTest conditionalSetToExample2Ingestion;
    @Inject
    private ConditionalSetToExample3IngestionTest conditionalSetToExample3Ingestion;
    @Inject
    private ConditionalSetToExample4IngestionTest conditionalSetToExample4Ingestion;
    @Inject
    private ConditionalSetToExample5IngestionTest conditionalSetToExample5Ingestion;
    @Inject
    private ConditionalSetToExample6IngestionTest conditionalSetToExample6Ingestion;
    @Inject
    private ConditionalSetToExample7IngestionTest conditionalSetToExample7Ingestion;
    @Inject
    private ConditionalSetToExample8IngestionTest conditionalSetToExample8Ingestion;
    @Inject
    private ConditionalSetToExample9IngestionTest conditionalSetToExample9Ingestion;
    @Inject
    private ConditionalSetToExample10IngestionTest conditionalSetToExample10Ingestion;
    @Inject
    private ConditionalSetToExample11IngestionTest conditionalSetToExample11Ingestion;
    @Inject
    private ConditionalSetToExample12IngestionTest conditionalSetToExample12Ingestion;
    @Inject
    private ConditionalSetToExample13IngestionTest conditionalSetToExample13Ingestion;
    @Inject
    private ExternalSynonymExample1IngestionTest externalSynonymExample1Ingestion;
    @Inject
    private ExternalSynonymExample2IngestionTest externalSynonymExample2Ingestion;
    @Inject
    private ExternalSynonymExample3IngestionTest externalSynonymExample3Ingestion;
    @Inject
    private ExternalSynonymExample4IngestionTest externalSynonymExample4Ingestion;
    @Inject
    private ExternalSynonymExample5IngestionTest externalSynonymExample5Ingestion;
    @Inject
    private ExternalSynonymExample6IngestionTest externalSynonymExample6Ingestion;
    @Inject
    private ExternalSynonymExample7IngestionTest externalSynonymExample7Ingestion;
    @Inject
    private ExternalSynonymExample8IngestionTest externalSynonymExample8Ingestion;
    @Inject
    private FormatExample1IngestionTest formatExample1Ingestion;
    @Inject
    private FormatExample2IngestionTest formatExample2Ingestion;
    @Inject
    private MappersExample1IngestionTest mappersExample1Ingestion;
    @Inject
    private MappersExample2IngestionTest mappersExample2Ingestion;
    @Inject
    private MappersExample3IngestionTest mappersExample3Ingestion;
    @Inject
    private MappersExample4IngestionTest mappersExample4Ingestion;
    @Inject
    private MappersExample5IngestionTest mappersExample5Ingestion;
    @Inject
    private MappersExample6IngestionTest mappersExample6Ingestion;
    @Inject
    private MappersExample7IngestionTest mappersExample7Ingestion;
    @Inject
    private MappersExample8IngestionTest mappersExample8Ingestion;
    @Inject
    private MappersExample9IngestionTest mappersExample9Ingestion;
    @Inject
    private MappersExample10IngestionTest mappersExample10Ingestion;
    @Inject
    private MappersExample11IngestionTest mappersExample11Ingestion;
    @Inject
    private MappersExample12IngestionTest mappersExample12Ingestion;
    @Inject
    private MetaExternalReferenceExample1IngestionTest metaExternalReferenceExample1Ingestion;
    @Inject
    private MetaExternalReferenceExample2IngestionTest metaExternalReferenceExample2Ingestion;
    @Inject
    private MetaExternalReferenceExample3IngestionTest metaExternalReferenceExample3Ingestion;
    @Inject
    private MetaExternalReferenceExample4IngestionTest metaExternalReferenceExample4Ingestion;
    @Inject
    private MetaExternalReferenceExample5IngestionTest metaExternalReferenceExample5Ingestion;
    @Inject
    private MetaExternalReferenceExample6IngestionTest metaExternalReferenceExample6Ingestion;
    @Inject
    private MetaSchemeExample1IngestionTest metaSchemeExample1Ingestion;
    @Inject
    private MetaScopedReferenceExample1IngestionTest metaScopedReferenceExample1Ingestion;
    @Inject
    private MetaScopedReferenceExample2IngestionTest metaScopedReferenceExample2Ingestion;
    @Inject
    private MultiCardinalityExample1IngestionTest multiCardinalityExample1Ingestion;
    @Inject
    private MultiCardinalityExample2IngestionTest multiCardinalityExample2Ingestion;
    @Inject
    private MultiCardinalityExample3IngestionTest multiCardinalityExample3Ingestion;
    @Inject
    private MultiCardinalityExample4IngestionTest multiCardinalityExample4Ingestion;
    @Inject
    private MultiCardinalityExample5IngestionTest multiCardinalityExample5Ingestion;
    @Inject
    private MultiCardinalityExample6IngestionTest multiCardinalityExample6Ingestion;
    @Inject
    private MultiCardinalityExample7IngestionTest multiCardinalityExample7Ingestion;
    @Inject
    private MultiCardinalityExample8IngestionTest multiCardinalityExample8Ingestion;
    @Inject
    private MultiCardinalityExample9IngestionTest multiCardinalityExample9Ingestion;
    @Inject
    private MultiCardinalityExample10IngestionTest multiCardinalityExample10Ingestion;
    @Inject
    private MultiCardinalityExample11IngestionTest multiCardinalityExample11Ingestion;
    @Inject
    private MultiCardinalityExample12IngestionTest multiCardinalityExample12Ingestion;
    @Inject
    private MultiCardinalityExample13IngestionTest multiCardinalityExample13Ingestion;
    @Inject
    private MultiCardinalityExample14IngestionTest multiCardinalityExample14Ingestion;
    @Inject
    private MultiCardinalityExample15IngestionTest multiCardinalityExample15Ingestion;
    @Inject
    private MultiCardinalityExample16IngestionTest multiCardinalityExample16Ingestion;
    @Inject
    private MultiCardinalityExample17IngestionTest multiCardinalityExample17Ingestion;
    @Inject
    private MultiCardinalityExample18IngestionTest multiCardinalityExample18Ingestion;
    @Inject
    private MultiCardinalityExample19IngestionTest multiCardinalityExample19Ingestion;
    @Inject
    private MultiCardinalityExample20IngestionTest multiCardinalityExample20Ingestion;
    @Inject
    private MultiCardinalityExample21IngestionTest multiCardinalityExample21Ingestion;
    @Inject
    private MultiCardinalityExample22IngestionTest multiCardinalityExample22Ingestion;
    @Inject
    private MultiCardinalityExample23IngestionTest multiCardinalityExample23Ingestion;
    @Inject
    private MultiCardinalityExample24IngestionTest multiCardinalityExample24Ingestion;
    @Inject
    private MultiCardinalityExample25IngestionTest multiCardinalityExample25Ingestion;
    @Inject
    private MultiCardinalityExample26IngestionTest multiCardinalityExample26Ingestion;
    @Inject
    private MultiCardinalityExample27IngestionTest multiCardinalityExample27Ingestion;
    @Inject
    private MultiCardinalityExample28IngestionTest multiCardinalityExample28Ingestion;
    @Inject
    private SingleCardinalityExample1IngestionTest singleCardinalityExample1Ingestion;
    @Inject
    private SingleCardinalityExample2IngestionTest singleCardinalityExample2Ingestion;
    @Inject
    private SingleCardinalityExample3IngestionTest singleCardinalityExample3Ingestion;
    @Inject
    private SingleCardinalityExample4IngestionTest singleCardinalityExample4Ingestion;
    @Inject
    private SingleCardinalityExample5IngestionTest singleCardinalityExample5Ingestion;
    @Inject
    private SingleCardinalityExample6IngestionTest singleCardinalityExample6Ingestion;
    @Inject
    private TypeInheritanceExample1IngestionTest typeInheritanceExample1Ingestion;
    @Inject
    private TypeInheritanceExample2IngestionTest typeInheritanceExample2Ingestion;

    public static void main(String[] args) {
        try {
            DemoTestPackCreator demoTestPackCreator = new DemoTestPackCreator();
            Injector injector = new DemoTestRuntimeModule.InjectorProvider().getInjector();
            injector.injectMembers(demoTestPackCreator);
            Stopwatch t = Stopwatch.createStarted();

            demoTestPackCreator.runIngestion();

            LOGGER.info("Update Expectations took {}", t);

            System.exit(0);
        } catch (Exception e) {
            LOGGER.error("Error executing {}.main()", DemoTestPackCreator.class.getName(), e);
            System.exit(1);
        }
    }

    private void runIngestion() {

        LOGGER.info(" ** Updating expectations for Ingestion");
        conditionalDefaultExample1Ingestion.updateExpectations();
        conditionalSetExample1Ingestion.updateExpectations();
        conditionalSetExample2Ingestion.updateExpectations();
        conditionalSetExample3Ingestion.updateExpectations();
        conditionalSetExample4Ingestion.updateExpectations();
        conditionalSetExample5Ingestion.updateExpectations();
        conditionalSetExample6Ingestion.updateExpectations();
        conditionalSetExample7Ingestion.updateExpectations();
        conditionalSetExample8Ingestion.updateExpectations();
        conditionalSetExample9Ingestion.updateExpectations();
        conditionalSetExample10Ingestion.updateExpectations();
        conditionalSetExample11Ingestion.updateExpectations();
        conditionalSetExample12Ingestion.updateExpectations();
        conditionalSetExample13Ingestion.updateExpectations();
        conditionalSetExample14Ingestion.updateExpectations();
        conditionalSetExample15Ingestion.updateExpectations();
        conditionalSetExample16Ingestion.updateExpectations();
        conditionalSetExample17Ingestion.updateExpectations();
        conditionalSetExample18Ingestion.updateExpectations();
        conditionalSetExample19Ingestion.updateExpectations();
        conditionalSetExample20Ingestion.updateExpectations();
        conditionalSetExample21Ingestion.updateExpectations();
        conditionalSetExample22Ingestion.updateExpectations();
        conditionalSetExample23Ingestion.updateExpectations();
        conditionalSetExample24Ingestion.updateExpectations();
        conditionalSetExample25Ingestion.updateExpectations();
        conditionalSetExample26Ingestion.updateExpectations();
        conditionalSetToExample1Ingestion.updateExpectations();
        conditionalSetToExample2Ingestion.updateExpectations();
        conditionalSetToExample3Ingestion.updateExpectations();
        conditionalSetToExample4Ingestion.updateExpectations();
        conditionalSetToExample5Ingestion.updateExpectations();
        conditionalSetToExample6Ingestion.updateExpectations();
        conditionalSetToExample7Ingestion.updateExpectations();
        conditionalSetToExample8Ingestion.updateExpectations();
        conditionalSetToExample9Ingestion.updateExpectations();
        conditionalSetToExample10Ingestion.updateExpectations();
        conditionalSetToExample11Ingestion.updateExpectations();
        conditionalSetToExample12Ingestion.updateExpectations();
        conditionalSetToExample13Ingestion.updateExpectations();
        externalSynonymExample1Ingestion.updateExpectations();
        externalSynonymExample2Ingestion.updateExpectations();
        externalSynonymExample3Ingestion.updateExpectations();
        externalSynonymExample4Ingestion.updateExpectations();
        externalSynonymExample5Ingestion.updateExpectations();
        externalSynonymExample6Ingestion.updateExpectations();
        externalSynonymExample7Ingestion.updateExpectations();
        externalSynonymExample8Ingestion.updateExpectations();
        formatExample1Ingestion.updateExpectations();
        formatExample2Ingestion.updateExpectations();
        mappersExample1Ingestion.updateExpectations();
        mappersExample2Ingestion.updateExpectations();
        mappersExample3Ingestion.updateExpectations();
        mappersExample4Ingestion.updateExpectations();
        mappersExample5Ingestion.updateExpectations();
        mappersExample6Ingestion.updateExpectations();
        mappersExample7Ingestion.updateExpectations();
        mappersExample8Ingestion.updateExpectations();
        mappersExample9Ingestion.updateExpectations();
        mappersExample10Ingestion.updateExpectations();
        mappersExample11Ingestion.updateExpectations();
        mappersExample12Ingestion.updateExpectations();
        metaExternalReferenceExample1Ingestion.updateExpectations();
        metaExternalReferenceExample2Ingestion.updateExpectations();
        metaExternalReferenceExample3Ingestion.updateExpectations();
        metaExternalReferenceExample4Ingestion.updateExpectations();
        metaExternalReferenceExample5Ingestion.updateExpectations();
        metaExternalReferenceExample6Ingestion.updateExpectations();
        metaSchemeExample1Ingestion.updateExpectations();
        metaScopedReferenceExample1Ingestion.updateExpectations();
        metaScopedReferenceExample2Ingestion.updateExpectations();
        multiCardinalityExample1Ingestion.updateExpectations();
        multiCardinalityExample2Ingestion.updateExpectations();
        multiCardinalityExample3Ingestion.updateExpectations();
        multiCardinalityExample4Ingestion.updateExpectations();
        multiCardinalityExample5Ingestion.updateExpectations();
        multiCardinalityExample6Ingestion.updateExpectations();
        multiCardinalityExample7Ingestion.updateExpectations();
        multiCardinalityExample8Ingestion.updateExpectations();
        multiCardinalityExample9Ingestion.updateExpectations();
        multiCardinalityExample10Ingestion.updateExpectations();
        multiCardinalityExample11Ingestion.updateExpectations();
        multiCardinalityExample12Ingestion.updateExpectations();
        multiCardinalityExample13Ingestion.updateExpectations();
        multiCardinalityExample14Ingestion.updateExpectations();
        multiCardinalityExample15Ingestion.updateExpectations();
        multiCardinalityExample16Ingestion.updateExpectations();
        multiCardinalityExample17Ingestion.updateExpectations();
        multiCardinalityExample18Ingestion.updateExpectations();
        multiCardinalityExample19Ingestion.updateExpectations();
        multiCardinalityExample20Ingestion.updateExpectations();
        multiCardinalityExample21Ingestion.updateExpectations();
        multiCardinalityExample22Ingestion.updateExpectations();
        multiCardinalityExample23Ingestion.updateExpectations();
        multiCardinalityExample24Ingestion.updateExpectations();
        multiCardinalityExample25Ingestion.updateExpectations();
        multiCardinalityExample26Ingestion.updateExpectations();
        multiCardinalityExample27Ingestion.updateExpectations();
        multiCardinalityExample28Ingestion.updateExpectations();
        singleCardinalityExample1Ingestion.updateExpectations();
        singleCardinalityExample2Ingestion.updateExpectations();
        singleCardinalityExample3Ingestion.updateExpectations();
        singleCardinalityExample4Ingestion.updateExpectations();
        singleCardinalityExample5Ingestion.updateExpectations();
        singleCardinalityExample6Ingestion.updateExpectations();
        typeInheritanceExample1Ingestion.updateExpectations();
        typeInheritanceExample2Ingestion.updateExpectations();

    }

}
