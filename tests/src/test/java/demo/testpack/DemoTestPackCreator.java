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
    private static ConditionalDefaultExample1IngestionTest conditionalDefaultExample1Ingestion;
    @Inject
    private static ConditionalSetExample1IngestionTest conditionalSetExample1Ingestion;
    @Inject
    private static ConditionalSetExample2IngestionTest conditionalSetExample2Ingestion;
    @Inject
    private static ConditionalSetExample3IngestionTest conditionalSetExample3Ingestion;
    @Inject
    private static ConditionalSetExample4IngestionTest conditionalSetExample4Ingestion;
    @Inject
    private static ConditionalSetExample5IngestionTest conditionalSetExample5Ingestion;
    @Inject
    private static ConditionalSetExample6IngestionTest conditionalSetExample6Ingestion;
    @Inject
    private static ConditionalSetExample7IngestionTest conditionalSetExample7Ingestion;
    @Inject
    private static ConditionalSetExample8IngestionTest conditionalSetExample8Ingestion;
    @Inject
    private static ConditionalSetExample9IngestionTest conditionalSetExample9Ingestion;
    @Inject
    private static ConditionalSetExample10IngestionTest conditionalSetExample10Ingestion;
    @Inject
    private static ConditionalSetExample11IngestionTest conditionalSetExample11Ingestion;
    @Inject
    private static ConditionalSetExample12IngestionTest conditionalSetExample12Ingestion;
    @Inject
    private static ConditionalSetExample13IngestionTest conditionalSetExample13Ingestion;
    @Inject
    private static ConditionalSetExample14IngestionTest conditionalSetExample14Ingestion;
    @Inject
    private static ConditionalSetExample15IngestionTest conditionalSetExample15Ingestion;
    @Inject
    private static ConditionalSetExample16IngestionTest conditionalSetExample16Ingestion;
    @Inject
    private static ConditionalSetExample17IngestionTest conditionalSetExample17Ingestion;
    @Inject
    private static ConditionalSetExample18IngestionTest conditionalSetExample18Ingestion;
    @Inject
    private static ConditionalSetExample19IngestionTest conditionalSetExample19Ingestion;
    @Inject
    private static ConditionalSetExample20IngestionTest conditionalSetExample20Ingestion;
    @Inject
    private static ConditionalSetExample21IngestionTest conditionalSetExample21Ingestion;
    @Inject
    private static ConditionalSetExample22IngestionTest conditionalSetExample22Ingestion;
    @Inject
    private static ConditionalSetExample23IngestionTest conditionalSetExample23Ingestion;
    @Inject
    private static ConditionalSetExample24IngestionTest conditionalSetExample24Ingestion;
    @Inject
    private static ConditionalSetExample25IngestionTest conditionalSetExample25Ingestion;
    @Inject
    private static ConditionalSetExample26IngestionTest conditionalSetExample26Ingestion;
    @Inject
    private static ConditionalSetToExample1IngestionTest conditionalSetToExample1Ingestion;
    @Inject
    private static ConditionalSetToExample2IngestionTest conditionalSetToExample2Ingestion;
    @Inject
    private static ConditionalSetToExample3IngestionTest conditionalSetToExample3Ingestion;
    @Inject
    private static ConditionalSetToExample4IngestionTest conditionalSetToExample4Ingestion;
    @Inject
    private static ConditionalSetToExample5IngestionTest conditionalSetToExample5Ingestion;
    @Inject
    private static ConditionalSetToExample6IngestionTest conditionalSetToExample6Ingestion;
    @Inject
    private static ConditionalSetToExample7IngestionTest conditionalSetToExample7Ingestion;
    @Inject
    private static ConditionalSetToExample8IngestionTest conditionalSetToExample8Ingestion;
    @Inject
    private static ConditionalSetToExample9IngestionTest conditionalSetToExample9Ingestion;
    @Inject
    private static ConditionalSetToExample10IngestionTest conditionalSetToExample10Ingestion;
    @Inject
    private static ConditionalSetToExample11IngestionTest conditionalSetToExample11Ingestion;
    @Inject
    private static ConditionalSetToExample12IngestionTest conditionalSetToExample12Ingestion;
    @Inject
    private static ConditionalSetToExample13IngestionTest conditionalSetToExample13Ingestion;
    @Inject
    private static ExternalSynonymExample1IngestionTest externalSynonymExample1Ingestion;
    @Inject
    private static ExternalSynonymExample2IngestionTest externalSynonymExample2Ingestion;
    @Inject
    private static ExternalSynonymExample3IngestionTest externalSynonymExample3Ingestion;
    @Inject
    private static ExternalSynonymExample4IngestionTest externalSynonymExample4Ingestion;
    @Inject
    private static ExternalSynonymExample5IngestionTest externalSynonymExample5Ingestion;
    @Inject
    private static ExternalSynonymExample6IngestionTest externalSynonymExample6Ingestion;
    @Inject
    private static ExternalSynonymExample7IngestionTest externalSynonymExample7Ingestion;
    @Inject
    private static ExternalSynonymExample8IngestionTest externalSynonymExample8Ingestion;
    @Inject
    private static FormatExample1IngestionTest formatExample1Ingestion;
    @Inject
    private static FormatExample2IngestionTest formatExample2Ingestion;
    @Inject
    private static MappersExample1IngestionTest mappersExample1Ingestion;
    @Inject
    private static MappersExample2IngestionTest mappersExample2Ingestion;
    @Inject
    private static MappersExample3IngestionTest mappersExample3Ingestion;
    @Inject
    private static MappersExample4IngestionTest mappersExample4Ingestion;
    @Inject
    private static MappersExample5IngestionTest mappersExample5Ingestion;
    @Inject
    private static MappersExample6IngestionTest mappersExample6Ingestion;
    @Inject
    private static MappersExample7IngestionTest mappersExample7Ingestion;
    @Inject
    private static MappersExample8IngestionTest mappersExample8Ingestion;
    @Inject
    private static MappersExample9IngestionTest mappersExample9Ingestion;
    @Inject
    private static MappersExample10IngestionTest mappersExample10Ingestion;
    @Inject
    private static MappersExample11IngestionTest mappersExample11Ingestion;
    @Inject
    private static MappersExample12IngestionTest mappersExample12Ingestion;
    @Inject
    private static MetaExternalReferenceExample1IngestionTest metaExternalReferenceExample1Ingestion;
    @Inject
    private static MetaExternalReferenceExample2IngestionTest metaExternalReferenceExample2Ingestion;
    @Inject
    private static MetaExternalReferenceExample3IngestionTest metaExternalReferenceExample3Ingestion;
    @Inject
    private static MetaExternalReferenceExample4IngestionTest metaExternalReferenceExample4Ingestion;
    @Inject
    private static MetaExternalReferenceExample5IngestionTest metaExternalReferenceExample5Ingestion;
    @Inject
    private static MetaExternalReferenceExample6IngestionTest metaExternalReferenceExample6Ingestion;
    @Inject
    private static MetaSchemeExample1IngestionTest metaSchemeExample1Ingestion;
    @Inject
    private static MetaScopedReferenceExample1IngestionTest metaScopedReferenceExample1Ingestion;
    @Inject
    private static MetaScopedReferenceExample2IngestionTest metaScopedReferenceExample2Ingestion;
    @Inject
    private static MultiCardinalityExample1IngestionTest multiCardinalityExample1Ingestion;
    @Inject
    private static MultiCardinalityExample2IngestionTest multiCardinalityExample2Ingestion;
    @Inject
    private static MultiCardinalityExample3IngestionTest multiCardinalityExample3Ingestion;
    @Inject
    private static MultiCardinalityExample4IngestionTest multiCardinalityExample4Ingestion;
    @Inject
    private static MultiCardinalityExample5IngestionTest multiCardinalityExample5Ingestion;
    @Inject
    private static MultiCardinalityExample6IngestionTest multiCardinalityExample6Ingestion;
    @Inject
    private static MultiCardinalityExample7IngestionTest multiCardinalityExample7Ingestion;
    @Inject
    private static MultiCardinalityExample8IngestionTest multiCardinalityExample8Ingestion;
    @Inject
    private static MultiCardinalityExample9IngestionTest multiCardinalityExample9Ingestion;
    @Inject
    private static MultiCardinalityExample10IngestionTest multiCardinalityExample10Ingestion;
    @Inject
    private static MultiCardinalityExample11IngestionTest multiCardinalityExample11Ingestion;
    @Inject
    private static MultiCardinalityExample12IngestionTest multiCardinalityExample12Ingestion;
    @Inject
    private static MultiCardinalityExample13IngestionTest multiCardinalityExample13Ingestion;
    @Inject
    private static MultiCardinalityExample14IngestionTest multiCardinalityExample14Ingestion;
    @Inject
    private static MultiCardinalityExample15IngestionTest multiCardinalityExample15Ingestion;
    @Inject
    private static MultiCardinalityExample16IngestionTest multiCardinalityExample16Ingestion;
    @Inject
    private static MultiCardinalityExample17IngestionTest multiCardinalityExample17Ingestion;
    @Inject
    private static MultiCardinalityExample18IngestionTest multiCardinalityExample18Ingestion;
    @Inject
    private static MultiCardinalityExample19IngestionTest multiCardinalityExample19Ingestion;
    @Inject
    private static MultiCardinalityExample20IngestionTest multiCardinalityExample20Ingestion;
    @Inject
    private static MultiCardinalityExample21IngestionTest multiCardinalityExample21Ingestion;
    @Inject
    private static MultiCardinalityExample22IngestionTest multiCardinalityExample22Ingestion;
    @Inject
    private static MultiCardinalityExample23IngestionTest multiCardinalityExample23Ingestion;
    @Inject
    private static MultiCardinalityExample24IngestionTest multiCardinalityExample24Ingestion;
    @Inject
    private static MultiCardinalityExample25IngestionTest multiCardinalityExample25Ingestion;
    @Inject
    private static MultiCardinalityExample26IngestionTest multiCardinalityExample26Ingestion;
    @Inject
    private static MultiCardinalityExample27IngestionTest multiCardinalityExample27Ingestion;
    @Inject
    private static MultiCardinalityExample28IngestionTest multiCardinalityExample28Ingestion;
    @Inject
    private static SingleCardinalityExample1IngestionTest singleCardinalityExample1Ingestion;
    @Inject
    private static SingleCardinalityExample2IngestionTest singleCardinalityExample2Ingestion;
    @Inject
    private static SingleCardinalityExample3IngestionTest singleCardinalityExample3Ingestion;
    @Inject
    private static SingleCardinalityExample4IngestionTest singleCardinalityExample4Ingestion;
    @Inject
    private static SingleCardinalityExample5IngestionTest singleCardinalityExample5Ingestion;
    @Inject
    private static SingleCardinalityExample6IngestionTest singleCardinalityExample6Ingestion;
    @Inject
    private static TypeInheritanceExample1IngestionTest typeInheritanceExample1Ingestion;
    @Inject
    private static TypeInheritanceExample2IngestionTest typeInheritanceExample2Ingestion;
    
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
