package demo.translate.mappers.example_3.processor;

import com.regnosys.rosetta.common.translation.MappingContext;
import com.regnosys.rosetta.common.translation.MappingProcessor;
import com.regnosys.rosetta.common.translation.Path;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.MetaFields;

import java.util.List;

/**
 * The mapper class name must be in the form "<MapperName>MappingProcessor", and must extend MappingProcessor.
 */
@SuppressWarnings("unused") // Used in generated code
public class Example3MappingProcessor extends MappingProcessor {

    public Example3MappingProcessor(RosettaPath modelPath, List<Path> synonymPaths, MappingContext context) {
        super(modelPath, synonymPaths, context);
    }

    /**
     * Override the map method as this mapper is specified on a basic type (with a metadata annotation) attribute Z->str2Field
     */
    @Override
    public void map(Path xmlPath, RosettaModelObjectBuilder builder, RosettaModelObjectBuilder parent) {
        // parameter: xmlPath = engineType->engineDetail->metric->combustible (the path where the "mapper" syntax is specified)
        // parameter: builder = an instance of object FieldWithMetaString, where the value has been set to "Gasoline" (the value at the path where the mapper is specified)
        // parameter: parent = an instance of object EngineSpecificationBuilder that can be updated

        // cast builder object
        FieldWithMetaString.FieldWithMetaStringBuilder metaStringBuilder =
                (FieldWithMetaString.FieldWithMetaStringBuilder) builder;
        // set new value on builder object
        metaStringBuilder
                .setMeta(MetaFields.builder()
                        .setScheme("fuel-type-scheme"));
    }
}
