package com.example.teampredictionworldcup.dto.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MatchDTO(int id, LocalDate date, StadiumDTO stadium, String countryA, String countryB, LocalTime startTime, LocalTime endTime, Integer scoreA, Integer scoreB) {
}
