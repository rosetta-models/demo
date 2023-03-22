package com.regnosys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper;
import com.rosetta.model.lib.RosettaModelObject;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestUtils.class);

    public static <T extends RosettaModelObject> Executable assertJsonEquals(String expectedJson, String resultJson, Class<T> clazz) {
        if (expectedJson == null) {
            return () -> assertTrue(true, "Old JSON file does not exist so passing this test.");
        }

        return () -> {
            try {
                ObjectMapper objectMapper = RosettaObjectMapper.getNewMinimalRosettaObjectMapper();

                T expected = objectMapper.readValue(expectedJson, clazz);
                T result = objectMapper.readValue(resultJson, clazz);

                ObjectWriter objectWriter = objectMapper
                        .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                        .writerWithDefaultPrettyPrinter();

                String expectedJsonSorted = objectWriter.writeValueAsString(expected);
                String resultJsonSorted = objectWriter.writeValueAsString(result);

                assertEquals(
                        removeLineEndings(expectedJsonSorted),
                        removeLineEndings(resultJsonSorted));
            } catch (JsonProcessingException e) {
                LOGGER.error("Error asserting json", e);
            }
        };
    }

    private static String removeLineEndings(String json) {
        return json.replace("\r", "");
    }
}
