package com.example.teampredictionworldcup.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.example.teampredictionworldcup.utils.DateTimeFormats.DATE_FORMATTER;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String valueAsString = p.getValueAsString();
        if (valueAsString == null || valueAsString.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(valueAsString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IOException("Invaliddate format: %s".formatted(valueAsString), e);
        }
    }
}
