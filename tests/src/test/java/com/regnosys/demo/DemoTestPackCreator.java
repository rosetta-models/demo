package com.regnosys.demo;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.regnosys.rosetta.common.transform.PipelineModel;
import com.regnosys.rosetta.common.transform.TransformType;
import com.regnosys.testing.pipeline.PipelineConfigWriter;
import com.regnosys.testing.pipeline.PipelineTreeConfig;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.regnosys.demo.emissions.DemoTestRuntimeModule;
import demo.emissions.enrichment.functions.Enrich_VehicleOwnershipToReportableVehicle;
import demo.emissions.ingestion.csv.UserData;
import demo.emissions.ingestion.csv.functions.Ingest_UserDataToPerson;
import demo.emissions.ingestion.json.VehicleOrder;
import demo.emissions.ingestion.json.functions.Ingest_VehicleOrderToNewVehicleOwnershipTransaction;
import demo.emissions.ingestion.xml.ExternalVehicleAndLicenceData;
import demo.emissions.ingestion.xml.functions.Ingest_ExternalVehicleAndLicenceDataToVehicleOwnership;
import demo.emissions.model.NewVehicleOwnershipTransaction;
import demo.emissions.model.Person;
import demo.emissions.model.VehicleOwnership;
import demo.emissions.projection.IsoEmissionsReport;
import demo.emissions.projection.functions.Project_EuropeanParliamentReportToIsoEmissionsReport;
import demo.emissions.regulation.EuropeanParliamentReport;
import demo.emissions.regulation.ReportableVehicle;
import demo.emissions.regulation.reports.EuropeanParliamentEmissionPerformanceStandardsEUReportFunction;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

import static com.regnosys.testing.pipeline.PipelineFilter.startsWith;

public class DemoTestPackCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoTestPackCreator.class);
    public static final String MODEL_ID = "demo";

    private static final ImmutableMap<Class<?>, String> TYPE_TO_XML_CONFIG_MAP = ImmutableMap.<Class<?>, String>builder()
            .put(ExternalVehicleAndLicenceData.class,
                    "schemas/external-vehicle-and-licence-data/xml-config/external-vehicle-and-licence-data-rosetta-xml-config.json")
            .put(IsoEmissionsReport.class,
                    "schemas/iso-emissions/xml-config/iso-emissions-rosetta-xml-config.json")
            .build();

    private static final ImmutableMap<Class<?>, PipelineModel.Serialisation.Format> INPUT_SERIALISATION_FORMAT = ImmutableMap.<Class<?>, PipelineModel.Serialisation.Format>builder()
            // ingest
            .put(UserData.class, PipelineModel.Serialisation.Format.CSV)
            .put(VehicleOrder.class, PipelineModel.Serialisation.Format.RUNE_JSON)
            .put(ExternalVehicleAndLicenceData.class, PipelineModel.Serialisation.Format.XML)
            // enrich
//            .put(VehicleOwnership.class, PipelineModel.Serialisation.Format.RUNE_JSON)
            // report
//            .put(ReportableVehicle.class, PipelineModel.Serialisation.Format.RUNE_JSON)
            // projection
//            .put(EuropeanParliamentReport.class, PipelineModel.Serialisation.Format.RUNE_JSON)
            .build();

    private static final ImmutableMap<Class<?>, PipelineModel.Serialisation.Format> OUTPUT_SERIALISATION_FORMAT = ImmutableMap.<Class<?>, PipelineModel.Serialisation.Format>builder()
            // ingest
//            .put(Person.class, PipelineModel.Serialisation.Format.RUNE_JSON)
//            .put(NewVehicleOwnershipTransaction.class, PipelineModel.Serialisation.Format.RUNE_JSON)
//            .put(VehicleOwnership.class, PipelineModel.Serialisation.Format.RUNE_JSON)
            // enrich
//            .put(ReportableVehicle.class, PipelineModel.Serialisation.Format.RUNE_JSON)
            // report
//            .put(EuropeanParliamentReport.class, PipelineModel.Serialisation.Format.RUNE_JSON)
            // projection
            .put(IsoEmissionsReport.class, PipelineModel.Serialisation.Format.XML)
            .build();


    private static final ImmutableSet<Path> CSV_TEST_PACK_SOURCE_FILES = ImmutableSet.<Path>builder()
            .add(Path.of("ingest/input/users/users.csv"))
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
                        Ingest_ExternalVehicleAndLicenceDataToVehicleOwnership.class)
                        .add(Ingest_ExternalVehicleAndLicenceDataToVehicleOwnership.class, TransformType.ENRICH, Enrich_VehicleOwnershipToReportableVehicle.class)
                        .add(Enrich_VehicleOwnershipToReportableVehicle.class, TransformType.REPORT, EuropeanParliamentEmissionPerformanceStandardsEUReportFunction.class)
                        .add(EuropeanParliamentEmissionPerformanceStandardsEUReportFunction.class, TransformType.PROJECTION, Project_EuropeanParliamentReportToIsoEmissionsReport.class));

        pipelineConfigWriter.writePipelinesAndTestPacks(
                configure(prefix, "users", Ingest_UserDataToPerson.class)
        );

        pipelineConfigWriter.writePipelinesAndTestPacks(
                configure(prefix, "orders", Ingest_VehicleOrderToNewVehicleOwnershipTransaction.class)
        );
    }

    private PipelineTreeConfig configure(String modelPrefix, String testPackFilter,
                                         Class<? extends RosettaFunction> translateFunction) {
        return new PipelineTreeConfig(modelPrefix)
                .strictUniqueIds()
                .withTestPackIdFilter(startsWith(testPackFilter))
                .withXmlConfigMap(TYPE_TO_XML_CONFIG_MAP)
                .withInputSerialisationFormatMap(INPUT_SERIALISATION_FORMAT)
                .withOutputSerialisationFormatMap(OUTPUT_SERIALISATION_FORMAT)
                .withCsvTestPackSourceFiles(CSV_TEST_PACK_SOURCE_FILES)
                .starting(TransformType.TRANSLATE, translateFunction);
    }
}
