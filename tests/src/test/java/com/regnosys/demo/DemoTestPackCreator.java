package com.regnosys.demo;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.regnosys.rosetta.common.transform.PipelineModel;
import com.regnosys.rosetta.common.transform.TransformType;
import com.regnosys.testing.pipeline.PipelineConfigWriter;
import com.regnosys.testing.pipeline.PipelineTreeConfig;
import com.rosetta.model.lib.functions.RosettaFunction;
import demo.csv.ingestion.User;
import demo.csv.ingestion.functions.Ingest_User;
import demo.emissions.DemoTestRuntimeModule;
import demo.emissions.ingestion.ExternalVehicleAndLicenceData;
import demo.emissions.ingestion.functions.Ingest_ExternalVehicleAndLicenceDataToVehicleOwnership;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.regnosys.testing.pipeline.PipelineFilter.startsWith;

public class DemoTestPackCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoTestPackCreator.class);
    public static final String MODEL_ID = "demo";

    private static final ImmutableMap<Class<?>, String> typeToXmlConfigMap = ImmutableMap.<Class<?>, String>builder()
            .put(ExternalVehicleAndLicenceData.class,
                    "schemas/external-vehicle-and-licence-data/xml-config/external-vehicle-and-licence-data-rosetta-xml-config.json")
            .build();

    private static final ImmutableMap<Class<?>, PipelineModel.Serialisation.Format> inputSerialisationFormat = ImmutableMap.<Class<?>, PipelineModel.Serialisation.Format>builder()
            .put(User.class, PipelineModel.Serialisation.Format.CSV)
            .build();

    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new DemoTestRuntimeModule());
            DemoTestPackCreator creator = injector.getInstance(DemoTestPackCreator.class);
            creator.run(MODEL_ID);
            System.exit(0);
        } catch (Exception e) {
            LOGGER.error("Error executing {}.main()", DemoTestPackCreator.class.getName(), e);
            System.exit(1);
        }
    }

    @Inject
    private PipelineConfigWriter pipelineConfigWriter;

    void run(String prefix) throws IOException {
        pipelineConfigWriter.writePipelinesAndTestPacks(
                configure(prefix, "passenger-vehicles",
                        Ingest_ExternalVehicleAndLicenceDataToVehicleOwnership.class));

        pipelineConfigWriter.writePipelinesAndTestPacks(
                configure(prefix, "users", Ingest_User.class)
        );
    }

    private PipelineTreeConfig configure(String modelPrefix, String testPackFilter,
                                         Class<? extends RosettaFunction> translateFunction) {
        return new PipelineTreeConfig(modelPrefix)
                .strictUniqueIds()
                .withTestPackIdFilter(startsWith(testPackFilter))
                .withXmlConfigMap(typeToXmlConfigMap)
                .withInputSerialisationFormatMap(inputSerialisationFormat)
                .starting(TransformType.TRANSLATE, translateFunction);
    }
}
