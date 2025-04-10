package demo.emissions;

import com.regnosys.rosetta.common.transform.TestPackModel;
import com.regnosys.rosetta.common.transform.TransformType;
import com.regnosys.testing.transform.TransformTestExtension;
import demo.emissions.projection.functions.Project_EuropeanParliamentReportToIsoEmissionsReport;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InjectionExtension.class)
@InjectWith(DemoTestRuntimeModule.InjectorProvider.class)
class EmissionsProjectionTest {

    public static final Path PROJECTION_CONFIG_PATH = Paths.get(TransformType.PROJECTION.getResourcePath()).resolve("config");

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
