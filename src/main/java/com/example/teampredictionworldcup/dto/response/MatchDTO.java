package com.example.teampredictionworldcup.dto.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record MatchDTO(int id, LocalDateTime date, StadiumDTO stadium, String countryA, String countryB) {
}
