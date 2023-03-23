package demo.translate.mappers.example_8.processor;

import com.regnosys.rosetta.common.translation.MappingContext;
import com.regnosys.rosetta.common.translation.MappingProcessor;
import com.regnosys.rosetta.common.translation.Path;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.path.RosettaPath;

import java.util.List;

import static demo.translate.mappers.example_8.EngineSpecification.EngineSpecificationBuilder;

/**
 * The mapper class name must be in the form "<MapperName>MappingProcessor", and must extend MappingProcessor.
 */
@SuppressWarnings("unused") // Used in generated code
public class Example8MappingProcessor extends MappingProcessor {

    public Example8MappingProcessor(RosettaPath modelPath, List<Path> synonymPaths, MappingContext context) {
        super(modelPath, synonymPaths, context);
    }

    /**
     * Override the map method as this mapper is specified on a list attribute Root->engineSpecification
     */
    @Override
    public void map(Path xmlPath, List<? extends RosettaModelObjectBuilder> builder, RosettaModelObjectBuilder parent) {
        List<EngineSpecificationBuilder> engineSpecificationBuilders = (List<EngineSpecificationBuilder>) builder;

        engineSpecificationBuilders.forEach(engineSpecificationBuilder -> {
            String emissions = engineSpecificationBuilder.getEmissions() + "_Updated";
            engineSpecificationBuilder.setEmissions(emissions);

            String fuelType = engineSpecificationBuilder.getFuelType() + "_Updated";
            engineSpecificationBuilder.setFuelType(fuelType);

            String volumeUnit = engineSpecificationBuilder.getVolumeUnit() + "_Updated";
            engineSpecificationBuilder.setVolumeUnit(volumeUnit);
        });
    }
}
