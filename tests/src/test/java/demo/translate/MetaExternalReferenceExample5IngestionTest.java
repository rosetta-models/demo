package demo.translate;

import com.regnosys.TestUtils;
import com.regnosys.ingest.test.framework.ingestor.ExpectationUtil;
import com.regnosys.ingest.test.framework.ingestor.IngestionTest;
import com.regnosys.ingest.test.framework.ingestor.IngestionTestUtil;
import com.regnosys.ingest.test.framework.ingestor.service.IngestionFactory;
import com.regnosys.ingest.test.framework.ingestor.service.IngestionService;
import com.regnosys.model.ModelRuntimeModule;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.Arguments;

import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

//@org.junit.jupiter.api.Disabled
public class MetaExternalReferenceExample5IngestionTest extends IngestionTest<demo.translate.meta_external_reference.example_5.Root> {

    private static final String SAMPLE_FILES_DIR = "cdm-sample-files/meta-external-reference/example-5";
    private static final String INSTANCE_NAME = "target/META_EXTERNAL_REFERENCE_EXAMPLE_5";

    private static IngestionService ingestionService;

    @BeforeAll
    static void setup() {
        writeActualExpectations = ExpectationUtil.WRITE_EXPECTATIONS;

        ClassLoader cl = MetaExternalReferenceExample5IngestionTest.class.getClassLoader();
        Collection<URL> ingestURLs = List.of(
                Objects.requireNonNull(cl.getResource("ingestions/meta-external-reference-example-5-ingestions.json")));
        ModelRuntimeModule runtimeModule = new ModelRuntimeModule();
        initialiseIngestionFactory(INSTANCE_NAME, ingestURLs, runtimeModule, new ArrayList<>(IngestionTestUtil.getPostProcessors(runtimeModule)));
        IngestionFactory factory = IngestionFactory.getInstance(INSTANCE_NAME);
        ingestionService = factory.getService("META_EXTERNAL_REFERENCE_EXAMPLE_5");
    }

    @Override
    protected Class<demo.translate.meta_external_reference.example_5.Root> getClazz() {
        return demo.translate.meta_external_reference.example_5.Root.class;
    }

    @Override
    protected IngestionService ingestionService() {
        return ingestionService;
    }

    @Override
    protected Executable assertJsonEquals(String expectedJson, String resultJson) {
        return TestUtils.assertJsonEquals(expectedJson, resultJson, getClazz());
    }

    @SuppressWarnings("unused")//used by the junit parameterized test
    private static Stream<Arguments> fpMLFiles() {
        return readExpectationsFromPath(SAMPLE_FILES_DIR);
    }

}
