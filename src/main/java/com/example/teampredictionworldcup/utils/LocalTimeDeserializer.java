package com.example.teampredictionworldcup.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static com.example.teampredictionworldcup.utils.DateTimeFormats.TIME_FORMATTER;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String valueAsString = p.getValueAsString();
        if (valueAsString == null || valueAsString.isBlank()) {
            return null;
        }
        try {
            return LocalTime.parse(valueAsString, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IOException("Invalidtime format: %s".formatted(valueAsString), e);
        }
    }
}
