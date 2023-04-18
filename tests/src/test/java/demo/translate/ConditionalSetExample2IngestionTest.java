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
public class ConditionalSetExample2IngestionTest extends IngestionTest<demo.translate.conditional_set.example_2.Root> {

    private static final String SAMPLE_FILES_DIR = "cdm-sample-files/conditional-set/example-2";
    private static final String INSTANCE_NAME = "target/CONDITIONAL_SET_EXAMPLE_2";

    private static IngestionService ingestionService;

    @BeforeAll
    static void setup() {
        writeActualExpectations = ExpectationUtil.WRITE_EXPECTATIONS;

        ClassLoader cl = ConditionalSetExample2IngestionTest.class.getClassLoader();
        Collection<URL> ingestURLs = List.of(
                Objects.requireNonNull(cl.getResource("ingestions/conditional-set-example-2-ingestions.json")));
        ModelRuntimeModule runtimeModule = new ModelRuntimeModule();
        initialiseIngestionFactory(INSTANCE_NAME, ingestURLs, runtimeModule, new ArrayList<>(IngestionTestUtil.getPostProcessors(runtimeModule)));
        IngestionFactory factory = IngestionFactory.getInstance(INSTANCE_NAME);
        ingestionService = factory.getService("CONDITIONAL_SET_EXAMPLE_2");
    }

    @Override
    protected Class<demo.translate.conditional_set.example_2.Root> getClazz() {
        return demo.translate.conditional_set.example_2.Root.class;
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
