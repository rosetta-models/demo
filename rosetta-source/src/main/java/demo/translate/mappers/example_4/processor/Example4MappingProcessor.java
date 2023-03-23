package demo.translate.mappers.example_4.processor;

import com.regnosys.rosetta.common.translation.MappingContext;
import com.regnosys.rosetta.common.translation.MappingProcessor;
import com.regnosys.rosetta.common.translation.Path;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.path.RosettaPath;
import demo.translate.mappers.example_4.EngineSpecification;
import demo.translate.mappers.example_4.metafields.FieldWithMetaEngineSpecification;

import java.util.List;

/**
 * The mapper class name must be in the form "<MapperName>MappingProcessor", and must extend MappingProcessor.
 */
@SuppressWarnings("unused") // Used in generated code
public class Example4MappingProcessor extends MappingProcessor {

    public Example4MappingProcessor(RosettaPath modelPath, List<Path> synonymPaths, MappingContext context) {
        super(modelPath, synonymPaths, context);
    }

    /**
     * Override the map method as this mapper is specified on a complex type attribute Root->engineSpecification
     */
    @Override
    public void map(Path xmlPath, RosettaModelObjectBuilder builder, RosettaModelObjectBuilder parent) {
        // parameter: xmlPath = engineType (the path where the "mapper" syntax is specified)
        // parameter: builder = an instance of object FieldWithMetaEngineSpecificationBuilder that can be updated
        // parameter: parent = an instance of parent object RootBuilder that can be updated

        // engineType->engineDetail->metric->emissions (e.g. value of "Low-Emission")
        Path xmlPath1 = xmlPath.addElement("engineDetail").addElement("metric").addElement("emissions");
        // cast builder object
        FieldWithMetaEngineSpecification.FieldWithMetaEngineSpecificationBuilder metaEngineSpecificationBuilder =
                (FieldWithMetaEngineSpecification.FieldWithMetaEngineSpecificationBuilder) builder;

        // this helper function will look up a xml path, and pass it to the consumer function, then updates the mapping stats
        setValueAndUpdateMappings(xmlPath1,
                // consumer function that takes value found at the xml path
                xmlValueFromXmlPath1 -> {
                    // get value from meta type
                    EngineSpecification.EngineSpecificationBuilder engineSpecificationBuilder = metaEngineSpecificationBuilder.getOrCreateValue();
                    // set new value on builder object
                    engineSpecificationBuilder.setEmissions(xmlValueFromXmlPath1);
                });

        // engineType->engineDetail->metric->combustible (e.g. value of "Gasoline")
        Path xmlPath2 = xmlPath.addElement("engineDetail").addElement("metric").addElement("combustible");

        // this helper function will look up a xml path, and pass it to the consumer function, then updates the mapping stats
        setValueAndUpdateMappings(xmlPath2,
                // consumer function that takes value found at the xml path
                xmlValueFromXmlPath2 -> {
                    // get value from meta type
                    EngineSpecification.EngineSpecificationBuilder engineSpecificationBuilder = metaEngineSpecificationBuilder.getOrCreateValue();
                    // set new value on builder object
                    engineSpecificationBuilder.setFuelType(xmlValueFromXmlPath2);
                });
    }
}
