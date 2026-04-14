package com.example.teampredictionworldcup.dto.response;

import java.time.LocalDateTime;

public record MatchDTO(LocalDateTime date, StadiumDTO stadium, String countryA, String countryB) {
}
