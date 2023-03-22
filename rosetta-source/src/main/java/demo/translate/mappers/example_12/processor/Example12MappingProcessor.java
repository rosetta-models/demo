package demo.translate.mappers.example_12.processor;

import com.regnosys.rosetta.common.translation.MappingContext;
import com.regnosys.rosetta.common.translation.MappingProcessor;
import com.regnosys.rosetta.common.translation.Path;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.path.RosettaPath;
import demo.translate.mappers.example_12.EngineSpecification;

import java.util.List;
import java.util.Optional;

import static demo.translate.mappers.example_12.Engine.EngineBuilder;
import static demo.translate.mappers.example_12.EngineSpecification.EngineSpecificationBuilder;

/**
 * The mapper class name must be in the form "<MapperName>MappingProcessor", and must extend MappingProcessor.
 */
@SuppressWarnings("unused") // Used in generated code
public class Example12MappingProcessor extends MappingProcessor {

    public Example12MappingProcessor(RosettaPath modelPath, List<Path> synonymPaths, MappingContext context) {
        super(modelPath, synonymPaths, context);
    }

    /**
     * Override the map (list) method as this mapper is specified on a list type list attribute Engine->engineSpecification
     */
    @Override
    public void map(Path xmlPath, List<? extends RosettaModelObjectBuilder> builder, RosettaModelObjectBuilder parent) {
        // parameter: xmlPath = engineType.engineDetail (the path where the "mapper" syntax is specified)
        // parameter: builder = an empty list of EngineSpecificationBuilder (as nothing is mapped)
        // parameter: parent = an instance of EngineBuilder

        EngineBuilder engineBuilder = (EngineBuilder) parent;

        // Loop through the xml paths, incrementing the index until no xml elements are found
        int i = 0;
        while (true) {
            // Attempt to build engineSpecification
            Optional<? extends EngineSpecification> engineSpecificationBuilder =
                    getEngineSpecificationBuilder(xmlPath, i++);
            // If engineSpecification exists, add to the list and try the next index, otherwise break
            if (engineSpecificationBuilder.isPresent()) {
                engineBuilder.addEngineSpecification(engineSpecificationBuilder.get());
            } else {
                break;
            }
        }
    }

    private Optional<? extends EngineSpecification> getEngineSpecificationBuilder(Path engineTypePath, int pathIndex) {
        EngineSpecificationBuilder engineSpecificationBuilder = EngineSpecification.builder();

        // set from path engineType.fuelDetail(i)
        Path fuelDetailPath = engineTypePath.addElement("fuelDetail", pathIndex);

        // set from path engineType.fuelDetail(i).emissions
        setValueAndUpdateMappings(fuelDetailPath.addElement("emissions"),
                engineSpecificationBuilder::setEmissions);

        // set from path engineType.fuelDetail(i).combustible
        setValueAndUpdateMappings(fuelDetailPath.addElement("combustible"),
                engineSpecificationBuilder::setFuelType);

        // set from path engineType.capacityDetail(i)
        Path capacityDetailPath = engineTypePath.addElement("capacityDetail", pathIndex);

        // set from path engineType.capacityDetail(i).capacityUnit
        setValueAndUpdateMappings(capacityDetailPath.addElement("capacityUnit"),
                engineSpecificationBuilder::setVolumeConsumption);

        // set from path engineType.capacityDetail(i).volumeConsumption
        setValueAndUpdateMappings(capacityDetailPath.addElement("volumeConsumption"),
                engineSpecificationBuilder::setVolumeUnit);

        return engineSpecificationBuilder.hasData() ?
                Optional.of(engineSpecificationBuilder.build()) :
                Optional.empty();
    }
}