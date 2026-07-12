package com.regnosys.demo.emissions.projection;

import com.regnosys.rosetta.common.transform.TestPackModel;
import com.regnosys.testing.transform.TransformTestExtension;
import com.regnosys.demo.emissions.DemoTestRuntimeModule;
import demo.emissions.projection.functions.Project_EuropeanParliamentReportToIsoEmissionsReport;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.regnosys.rosetta.common.transform.TestPackUtils.PROJECTION_CONFIG_PATH;

class ProjectEuropeanParliamentReportToIsoEmissionsReportTest {

    @RegisterExtension
    static TransformTestExtension<Project_EuropeanParliamentReportToIsoEmissionsReport> testExtension =
            new TransformTestExtension<>(new DemoTestRuntimeModule(),
                    PROJECTION_CONFIG_PATH,
                    Project_EuropeanParliamentReportToIsoEmissionsReport.class);

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
