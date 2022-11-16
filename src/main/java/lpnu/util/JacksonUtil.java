package lpnu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JacksonUtil {

    public static <T> T deserialize(final String source, final Class<T> clazz) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(source, clazz);
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T deserialize(final String source, final TypeReference<T> type) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(source, type);
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String serialize(final Object object) {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}