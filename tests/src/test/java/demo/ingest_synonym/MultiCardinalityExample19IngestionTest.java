package demo.ingest_synonym;

import com.regnosys.ingest.test.framework.ingestor.ExpectationUtil;
import com.regnosys.ingest.test.framework.ingestor.IngestionTest;
import com.regnosys.ingest.test.framework.ingestor.IngestionTestUtil;
import com.regnosys.ingest.test.framework.ingestor.service.IngestionFactory;
import com.regnosys.ingest.test.framework.ingestor.service.IngestionService;
import com.regnosys.demo.DemoRuntimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.Arguments;

import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

//@org.junit.jupiter.api.Disabled
public class MultiCardinalityExample19IngestionTest extends IngestionTest<demo.ingest_synonym.multi_cardinality.example_19.Root> {

    private static final String SAMPLE_FILES_DIR = "cdm-sample-files/multi-cardinality/example-19";
    private static final String INSTANCE_NAME = "target/MULTI_CARDINALITY_EXAMPLE_19";

    private static IngestionService ingestionService;

    @BeforeAll
    static void setup() {
        writeActualExpectations = ExpectationUtil.WRITE_EXPECTATIONS;

        ClassLoader cl = MultiCardinalityExample19IngestionTest.class.getClassLoader();
        Collection<URL> ingestURLs = List.of(
                Objects.requireNonNull(cl.getResource("ingestions/multi-cardinality-example-19-ingestions.json")));
        DemoRuntimeModule runtimeModule = new DemoRuntimeModule();
        initialiseIngestionFactory(INSTANCE_NAME, ingestURLs, runtimeModule, new ArrayList<>(IngestionTestUtil.getPostProcessors(runtimeModule)));
        IngestionFactory factory = IngestionFactory.getInstance(INSTANCE_NAME);
        ingestionService = factory.getService("MULTI_CARDINALITY_EXAMPLE_19");
    }

    @Override
    protected Class<demo.ingest_synonym.multi_cardinality.example_19.Root> getClazz() {
        return demo.ingest_synonym.multi_cardinality.example_19.Root.class;
    }

    @Override
    protected IngestionService ingestionService() {
        return ingestionService;
    }

    @SuppressWarnings("unused")//used by the junit parameterized test
    private static Stream<Arguments> fpMLFiles() {
        return readExpectationsFromPath(SAMPLE_FILES_DIR);
    }
}
