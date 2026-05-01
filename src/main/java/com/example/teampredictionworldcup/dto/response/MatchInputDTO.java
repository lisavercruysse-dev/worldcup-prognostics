package com.example.teampredictionworldcup.dto.response;

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

        @NotNull(message = "Date must be filled in.")
        LocalDate date,

        @NotNull(message = "Starttime must be filled in.")
        LocalTime starttime,

        @NotNull(message = "Endtime must be filled in.")
        LocalTime endtime,

        @NotNull(message = "Stadiumcode must be filled in.")
        @DecimalMin(value = "1000", message = "Must consist of 4 numbers.")
        @DecimalMax("9999")
        Integer stadiumCode,

        @NotNull(message = "Checksum must be filled in.")
        @Min(value = 0, message = "Must be at least 0.")
        Integer checksum,

        @NotBlank(message = "Stadiumname must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z ]+", message = "Must consist of letters an spaces only.")
        String stadiumName,

        @NotBlank(message = "City must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z]+", message = "Must consist of letters only.")
        String city) {
}
