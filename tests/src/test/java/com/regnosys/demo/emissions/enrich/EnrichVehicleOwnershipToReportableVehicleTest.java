package com.regnosys.demo.emissions.enrich;

import com.regnosys.rosetta.common.transform.TestPackModel;
import com.regnosys.rosetta.common.transform.TransformType;
import com.regnosys.testing.transform.TransformTestExtension;
import com.regnosys.demo.emissions.DemoTestRuntimeModule;
import demo.emissions.enrichment.functions.Enrich_VehicleOwnershipToReportableVehicle;
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

class EnrichVehicleOwnershipToReportableVehicleTest {

    public static final Path ENRICH_CONFIG_PATH = Paths.get(TransformType.ENRICH.getResourcePath()).resolve("config");

    @RegisterExtension
    static TransformTestExtension<Enrich_VehicleOwnershipToReportableVehicle> testExtension =
            new TransformTestExtension<>(new DemoTestRuntimeModule(),
                    ENRICH_CONFIG_PATH,
                    Enrich_VehicleOwnershipToReportableVehicle.class);

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
