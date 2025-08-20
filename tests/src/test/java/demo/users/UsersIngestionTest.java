package demo.users;

import com.regnosys.rosetta.common.transform.TestPackModel;
import com.regnosys.rosetta.common.transform.TransformType;
import com.regnosys.testing.transform.TransformTestExtension;
import demo.csv.ingestion.functions.Ingest_User;
import demo.emissions.DemoTestRuntimeModule;
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
public class UsersIngestionTest {

    public static final Path INGEST_CONFIG_PATH = Paths.get(TransformType.TRANSLATE.getResourcePath()).resolve("config");

    @RegisterExtension
    static TransformTestExtension<Ingest_User> testExtension =
            new TransformTestExtension<>(new DemoTestRuntimeModule(),
                    INGEST_CONFIG_PATH,
                    Ingest_User.class);

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
