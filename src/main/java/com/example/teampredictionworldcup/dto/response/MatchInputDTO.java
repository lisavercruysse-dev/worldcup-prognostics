package com.example.teampredictionworldcup.dto.response;

import com.example.teampredictionworldcup.validator.ValidDate;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MatchInputDTO(
        Integer id,

        @NotBlank(message = "Country A must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z]+", message = "Must consist of letters only.")
        @Size(min = 4, message = "Must be at least 4 letters long")
        String countryA,

        @NotBlank(message = "Country B must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z]+", message = "Must consist of letters only.")
        @Size(min = 4, message = "Must be at least 4 letters long.")
        String countryB,

        @ValidDate
        @NotNull(message = "Date must be filled in.")
        LocalDate date,

        @NotNull(message = "Starttime must be filled in.")
        LocalTime starttime,

        @NotNull(message = "Endtime must be filled in.")
        LocalTime endtime,

        @NotNull
        Integer stadiumCode) {}
