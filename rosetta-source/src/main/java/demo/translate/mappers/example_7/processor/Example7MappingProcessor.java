package demo.translate.mappers.example_7.processor;

import com.regnosys.rosetta.common.translation.MappingContext;
import com.regnosys.rosetta.common.translation.MappingProcessor;
import com.regnosys.rosetta.common.translation.Path;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.path.RosettaPath;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static demo.translate.mappers.example_7.EngineSpecification.EngineSpecificationBuilder;

/**
 * The mapper class name must be in the form "<MapperName>MappingProcessor", and must extend MappingProcessor.
 */
@SuppressWarnings("unused") // Used in generated code
public class Example7MappingProcessor extends MappingProcessor {

    public Example7MappingProcessor(RosettaPath modelPath, List<Path> synonymPaths, MappingContext context) {
        super(modelPath, synonymPaths, context);
    }

    /**
     * Override the mapBasic method as this mapper is specified on a basic type list attribute EngineSpecification->fuelType
     */
    @Override
    public <T> void mapBasic(Path xmlPath, Collection<? extends T> builders, RosettaModelObjectBuilder parent) {
        Collection<String> stringBuilders = (Collection<String>) builders;
        EngineSpecificationBuilder zBuilder = (EngineSpecificationBuilder) parent;

        // Create new list of strings
        List<String> updatedValues = stringBuilders.stream()
                .map(str -> str + "_Updated")
                .collect(Collectors.toList());

        // Update the parent with the new list of string values
        zBuilder.setFuelType(updatedValues);

       // TODO update existing mappings with new values
    }
}
