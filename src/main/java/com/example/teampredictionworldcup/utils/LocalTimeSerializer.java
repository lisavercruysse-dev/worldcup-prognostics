package com.example.teampredictionworldcup.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;

import static com.example.teampredictionworldcup.utils.DateTimeFormats.TIME_FORMATTER;

public class LocalTimeSerializer extends JsonSerializer<LocalTime> {

    @Override
    public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.format(TIME_FORMATTER));
    }
}
