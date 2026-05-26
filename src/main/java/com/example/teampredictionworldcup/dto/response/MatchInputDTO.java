package com.example.teampredictionworldcup.dto.response;

import com.example.teampredictionworldcup.validator.ValidDate;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MatchInputDTO(
        Integer id,

        @NotBlank()
        @Pattern(regexp =   "^[a-zA-Z]+", message = "{validation.country.Pattern.message}")
        @Size(min = 4, message = "{validation.country.Size.message}")
        String countryA,

        @NotBlank()
        @Pattern(regexp =   "^[a-zA-Z]+", message = "{validation.country.Pattern.message}")
        @Size(min = 4, message = "{validation.country.Size.message}")
        String countryB,

        @ValidDate
        @NotNull()
        LocalDate date,

        @NotNull()
        LocalTime starttime,

        @NotNull()
        LocalTime endtime,

        @NotNull
        Integer stadiumCode) {}
