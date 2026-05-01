package com.example.teampredictionworldcup.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MatchMinimalDTO(LocalDate date, String countryA, String countryB) {
}
