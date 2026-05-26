package com.example.teampredictionworldcup.exceptions;

public class StadiumNotFoundException extends RuntimeException {
    public StadiumNotFoundException(Integer id) {
        super("Could not find stadium %d".formatted(id));
    }
}
