package demo.translate.mappers.example_1.processor;

import com.regnosys.rosetta.common.translation.MappingContext;
import com.regnosys.rosetta.common.translation.MappingProcessor;
import com.regnosys.rosetta.common.translation.Path;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.path.RosettaPath;
import demo.translate.mappers.example_1.EngineSpecification;

import java.util.List;

/**
 * The mapper class name must be in the form "<MapperName>MappingProcessor", and must extend MappingProcessor.
 */
@SuppressWarnings("unused") // Used in generated code
public class Example1MappingProcessor extends MappingProcessor {

    public Example1MappingProcessor(RosettaPath modelPath, List<Path> synonymPaths, MappingContext context) {
        super(modelPath, synonymPaths, context);
    }

    /**
     * Override the mapBasic method as this mapper is specified on a basic type attribute EngineSpecification->fuelType
     */
    @Override
    public <T> void mapBasic(Path xmlPath, T xmlValueFromXmlPath, RosettaModelObjectBuilder parent) {
        // parameter: xmlPath = engineType->engineDetail->metric->combustible (the path where the "mapper" syntax is specified)
        // parameter: xmlValueFromXmlPath = "Gasoline" (the value at the path where the mapper is specified)
        // parameter: parent = an instance of object EngineSpecificationBuilder that can be updated

        Path parentXmlPath = xmlPath.getParent(); // engineType->engineDetail->metric
        Path newXmlPath = parentXmlPath.addElement("capacityUnit"); // engineType->engineDetail->metric->capacityUnit (e.g. value of "Gallon")

        // this helper function will look up a xml path, and pass it to the consumer function, then updates the mapping stats
        setValueAndUpdateMappings(newXmlPath,
                // consumer function that takes value found at the xml path
                xmlValueFromNewXmlPath -> {
                    // cast builder object
                    EngineSpecification.EngineSpecificationBuilder engineSpecificationBuilder =
                            (EngineSpecification.EngineSpecificationBuilder) parent;
                    // create new value to set, e.g. Gasoline_Gallon
                    String newValue = xmlValueFromXmlPath + "_" + xmlValueFromNewXmlPath;
                    // set new value on builder object
                    engineSpecificationBuilder.setFuelType(newValue);
                });
    }
}
