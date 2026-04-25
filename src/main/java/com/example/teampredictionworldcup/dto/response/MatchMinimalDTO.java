package com.example.teampredictionworldcup.dto.response;

import java.time.LocalDateTime;

public record MatchMinimalDTO(LocalDateTime dateTime, String countryA, String countryB) {
}
