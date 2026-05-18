package demo.ingest_synonym;

import com.regnosys.demo.DemoRuntimeModule;
import com.regnosys.ingest.test.framework.ingestor.IngestionTest;
import com.regnosys.ingest.test.framework.ingestor.IngestionTestUtil;
import com.regnosys.ingest.test.framework.ingestor.service.IngestionFactory;
import com.regnosys.ingest.test.framework.ingestor.service.IngestionService;
import com.regnosys.ingest.test.framework.ingestor.testing.Expectation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.Arguments;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

//@org.junit.jupiter.api.Disabled
public class MultiCardinalityExample8IngestionTest extends IngestionTest<demo.ingest_synonym.multi_cardinality.example_8.Root> {

    private static final String SAMPLE_FILES_DIR = "cdm-sample-files/multi-cardinality/example-8";
    private static final String INSTANCE_NAME = "target/MULTI_CARDINALITY_EXAMPLE_8";

    private static IngestionService ingestionService;

    @BeforeAll
    static void setup() {
        ClassLoader cl = MultiCardinalityExample8IngestionTest.class.getClassLoader();
        Collection<URL> ingestURLs = List.of(
                Objects.requireNonNull(cl.getResource("ingestions/multi-cardinality-example-8-ingestions.json")));
        DemoRuntimeModule runtimeModule = new DemoRuntimeModule();
        initialiseIngestionFactory(INSTANCE_NAME, ingestURLs, runtimeModule, new ArrayList<>(IngestionTestUtil.getPostProcessors(runtimeModule)));
        IngestionFactory factory = IngestionFactory.getInstance(INSTANCE_NAME);
        ingestionService = factory.getService("MULTI_CARDINALITY_EXAMPLE_8");
    }

    @Override
    protected Class<demo.ingest_synonym.multi_cardinality.example_8.Root> getClazz() {
        return demo.ingest_synonym.multi_cardinality.example_8.Root.class;
    }

    @Override
    protected IngestionService ingestionService() {
        return ingestionService;
    }

    @SuppressWarnings("unused")//used by the junit parameterized test
private static Stream<Arguments> fpMLFiles() {
        return readExpectationsFromPath(SAMPLE_FILES_DIR);
    }


    public void updateExpectations() {

        // Ensure environment is set up
        setup();
        fpMLFiles().forEach(e -> {
            Object[] argsArray = e.get();
            String expectationFilePath = (String) argsArray[0];
            Expectation expectation = (Expectation) argsArray[1];
            try {
                writeIngestionExpectation(expectationFilePath, expectation);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }

        });
    }
}
