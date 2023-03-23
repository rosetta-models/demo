package demo.translate.mappers.example_11.processor;

import com.regnosys.rosetta.common.translation.MappingContext;
import com.regnosys.rosetta.common.translation.MappingProcessor;
import com.regnosys.rosetta.common.translation.Path;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.metafields.MetaFields;

import java.util.List;

import static demo.translate.mappers.example_11.metafields.FieldWithMetaEngineSpecification.FieldWithMetaEngineSpecificationBuilder;

/**
 * The mapper class name must be in the form "<MapperName>MappingProcessor", and must extend MappingProcessor.
 */
@SuppressWarnings("unused") // Used in generated code
public class Example11MappingProcessor extends MappingProcessor {

    public Example11MappingProcessor(RosettaPath modelPath, List<Path> synonymPaths, MappingContext context) {
        super(modelPath, synonymPaths, context);
    }

    @Override
    public void map(Path synonymPath, List<? extends RosettaModelObjectBuilder> builders, RosettaModelObjectBuilder parent) {
        List<FieldWithMetaEngineSpecificationBuilder> metaEngineSpecificationBuilders =
                (List<FieldWithMetaEngineSpecificationBuilder>) builders;
        // set scheme on each builder object
        metaEngineSpecificationBuilders.forEach(builder ->
                builder.setMeta(MetaFields.builder()
                        .setScheme("engine-specification-scheme")));

    }
}