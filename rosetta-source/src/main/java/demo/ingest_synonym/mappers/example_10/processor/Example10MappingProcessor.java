package demo.ingest_synonym.mappers.example_10.processor;

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
public class Example10MappingProcessor extends MappingProcessor {

    public Example10MappingProcessor(RosettaPath modelPath, List<Path> synonymPaths, MappingContext context) {
        super(modelPath, synonymPaths, context);
    }

    /**
     * Override the mapBasic method as this mapper is specified on a basic type list attribute EngineSpecificationBuilder->fuelType
     */
    @Override
    public void map(Path xmlPath, List<? extends RosettaModelObjectBuilder> builders, RosettaModelObjectBuilder parent) {
        // parameter: xmlPath = engineType->engineDetail->metric->combustible (the path where the "mapper" syntax is specified)
        // parameter: builder = a list of object FieldWithMetaString
        // parameter: parent = an instance of object EngineSpecificationBuilder that can be updated

        // cast builder object
        List<FieldWithMetaString.FieldWithMetaStringBuilder> metaStringBuilders =
                (List<FieldWithMetaString.FieldWithMetaStringBuilder>) builders;
        // set scheme on builder object
        metaStringBuilders.forEach(metaStringBuilder ->
                metaStringBuilder.setMeta(MetaFields.builder()
                        .setScheme("fuel-type-scheme")));
    }
}