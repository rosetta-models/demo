package com.regnosys.demo.emissions.ingest;

import com.regnosys.rosetta.common.transform.TestPackModel;
import com.regnosys.testing.transform.TransformTestExtension;
import com.regnosys.demo.emissions.DemoTestRuntimeModule;
import demo.emissions.ingestion.csv.functions.Ingest_UserDataToPerson;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.regnosys.rosetta.common.transform.TestPackUtils.INGEST_CONFIG_PATH;

class IngestUserDataToPersonTest {

    @RegisterExtension
    static TransformTestExtension<Ingest_UserDataToPerson> testExtension =
            new TransformTestExtension<>(new DemoTestRuntimeModule(),
                    INGEST_CONFIG_PATH,
                    Ingest_UserDataToPerson.class);

    @ParameterizedTest(name = "{0}")
    @MethodSource("inputFiles")
    void runTest(String testName,
                 String testPackId,
                 TestPackModel.SampleModel sampleModel) {
        testExtension.runTransformAndAssert(testPackId, sampleModel);
    }

    @SuppressWarnings("unused")//used by the junit parameterized test
    private static Stream<Arguments> inputFiles() {
        return testExtension.getArguments();
    }
}
