package demo.translate.mappers.example_9.processor;

import com.regnosys.rosetta.common.translation.MappingContext;
import com.regnosys.rosetta.common.translation.MappingProcessor;
import com.regnosys.rosetta.common.translation.Path;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.path.RosettaPath;
import demo.translate.mappers.example_9.EngineSpecification;
import demo.translate.mappers.example_9.Root.RootBuilder;

import java.util.List;
import java.util.Optional;

/**
 * The mapper class name must be in the form "<MapperName>MappingProcessor", and must extend MappingProcessor.
 */
@SuppressWarnings("unused") // Used in generated code
public class Example9MappingProcessor extends MappingProcessor {

    public Example9MappingProcessor(RosettaPath modelPath, List<Path> synonymPaths, MappingContext context) {
        super(modelPath, synonymPaths, context);
    }

    @Override
    public void map(Path xmlPath, List<? extends RosettaModelObjectBuilder> builder, RosettaModelObjectBuilder parent) {
        RootBuilder rootBuilder = (RootBuilder) parent;

        // Loop through the xml paths, incrementing the index until no xml elements are found
        int i = 0;
        while (true) {
            // Attempt to build z
            Optional<? extends EngineSpecification> engineSpecificationBuilder = getEngineSpecificationBuilder(xmlPath, i++);
            // If engineSpecification exists, add to the list and try the next index, otherwise break
            if (engineSpecificationBuilder.isPresent()) {
                rootBuilder.addEngineSpecification(engineSpecificationBuilder.get());
            } else {
                break;
            }
        }
    }

    private Optional<? extends EngineSpecification> getEngineSpecificationBuilder(Path engineTypePath, int pathIndex) {
        EngineSpecification.EngineSpecificationBuilder builder = EngineSpecification.builder();

        // engineTypePath is indexless, get the parent (dataDocument path) and add "engineType" element with index
        Path dataDocumentPath = engineTypePath.getParent();
        Path metricPath = dataDocumentPath.addElement("engineType", pathIndex).addElement("engineDetail").addElement("metric");

        setValueAndUpdateMappings(metricPath.addElement("emissions"),
                xmlValue -> builder.setEmissions(xmlValue));

        setValueAndUpdateMappings(metricPath.addElement("combustible"),
                xmlValue -> builder.setFuelType(xmlValue));

        setValueAndUpdateMappings(metricPath.addElement("capacityUnit"),
                xmlValue -> builder.setVolumeUnit(xmlValue));

        return builder.hasData() ? Optional.of(builder.build()) : Optional.empty();
    }
}