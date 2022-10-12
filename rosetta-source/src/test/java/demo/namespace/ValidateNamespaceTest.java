package demo.namespace;

import com.regnosys.testing.RosettaFileNameValidator;
import com.regnosys.testing.ValidationReport;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateNamespaceTest {


    @Test

    void validateFileNamesMatchNamespace() throws IOException {
        String modelShortName = "demo";
        Path path = Paths.get("src/main/rosetta");
        RosettaFileNameValidator validator = new RosettaFileNameValidator(modelShortName, path, null);

        ValidationReport validationReport = validator.validateFileNamesMatchNamespace();

        assertTrue(validationReport.getPassed(),validationReport.getErrors().toString());

    }

}