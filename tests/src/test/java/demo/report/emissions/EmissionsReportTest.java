package demo.report.emissions;

import com.google.common.collect.ImmutableList;
import com.regnosys.rosetta.common.reports.RegReportIdentifier;
import com.regnosys.rosetta.common.reports.RegReportPaths;
import com.regnosys.testing.reports.ReportDataItemExpectation;
import com.regnosys.testing.reports.ReportTestExtension;
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
public class EmissionsReportTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmissionsReportTest.class);

    private static final ImmutableList<String> ROSETTA_PATHS = ImmutableList.of("demo/rosetta");
    private static final Path OUTPUT_PATH = RegReportPaths.getDefault().getOutputRelativePath();
    private static final Path EXPECTATIONS_ROOT_PATH = OUTPUT_PATH.resolve("europeanparliamentemissionperformancestandardseu");

    @Inject
    EuropeanParliamentEmissionPerformanceStandardsEUReportFunction reportFunction;
    @Inject
    EuropeanParliamentEmissionPerformanceStandardsEUReportTabulator tabulator;

    @RegisterExtension
    static ReportTestExtension<VehicleOwnership> testExtension =
            new ReportTestExtension<>(new ReportTestRuntimeModule(), ROSETTA_PATHS, VehicleOwnership.class)
                    .withRootExpectationsPath(EXPECTATIONS_ROOT_PATH);

    @ParameterizedTest(name = "{0}")
    @MethodSource("inputFiles")
    void runReport(String testName,
                   RegReportIdentifier reportIdentifier,
                   String dataSetName,
                   VehicleOwnership vehicleOwnership,
                   ReportDataItemExpectation expectation) throws Throwable {
        LOGGER.info("Running report '{}' with data set '{}' file '{}'", reportIdentifier.getName(), dataSetName, expectation.getFileName());

        testExtension.assertTest(reportIdentifier, dataSetName, expectation, reportFunction, tabulator, vehicleOwnership);
    }

    @SuppressWarnings("unused")//used by the junit parameterized test
    private static Stream<Arguments> inputFiles() {
        return testExtension.getArguments();
    }
}
