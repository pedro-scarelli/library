package com.library.catalog.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class JSONUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T transform(byte[] payload, Class<T> type) {
        try {
            return MAPPER.readValue(payload, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
