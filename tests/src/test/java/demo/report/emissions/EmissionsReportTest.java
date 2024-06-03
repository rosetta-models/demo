package demo.report.emissions;

import com.regnosys.rosetta.common.reports.RegReportPaths;
import com.regnosys.rosetta.common.transform.TestPackModel;
import com.regnosys.testing.transform.TransformTestExtension;
import demo.emissions.VehicleOwnership;
import demo.emissions.reports.EuropeanParliamentEmissionPerformanceStandardsEUReportFunction;
import demo.emissions.reports.EuropeanParliamentEmissionPerformanceStandardsEUReportTabulator;
import demo.report.ReportTestRuntimeModule;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.nio.file.Path;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InjectionExtension.class)
@InjectWith(ReportTestRuntimeModule.InjectorProvider.class)
class EmissionsReportTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmissionsReportTest.class);

    private static final Path CONFIG_PATH = RegReportPaths.getDefault().getConfigRelativePath();
    private static final String testPackFileName = "test-pack-report-europeanparliament-emissionperformancestandardseu-drr vehicle ownership.json";


    @Inject
    EuropeanParliamentEmissionPerformanceStandardsEUReportFunction reportFunction;
    @Inject
    EuropeanParliamentEmissionPerformanceStandardsEUReportTabulator tabulator;

    @RegisterExtension
    static TransformTestExtension<VehicleOwnership> testExtension =
            new TransformTestExtension<>(new ReportTestRuntimeModule(), VehicleOwnership.class)
                    .withRootExpectationsPath(CONFIG_PATH)
                    .withTestPackFileName(testPackFileName);

    @ParameterizedTest(name = "{0}")
    @MethodSource("inputFiles")
    void runReport(String testPackId,
                   String pipeLineId,
                   String dataSetName,
                   VehicleOwnership vehicleOwnership,
                   TestPackModel.SampleModel sampleModel) throws Throwable {
        LOGGER.info("Running TestPack '{}' file '{}'", testPackId, sampleModel.getInputPath ());

        testExtension.runTransformAndAssert(testPackId, pipeLineId, dataSetName, sampleModel, reportFunction, tabulator, vehicleOwnership);
    }

    @SuppressWarnings("unused")//used by the junit parameterized test
    private static Stream<Arguments> inputFiles() {
        return testExtension.getArguments();
    }
}
