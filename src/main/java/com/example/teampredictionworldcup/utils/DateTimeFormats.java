package com.example.teampredictionworldcup.utils;


import java.time.format.DateTimeFormatter;

public class DateTimeFormats {
    private DateTimeFormats() {}

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
}
