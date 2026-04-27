package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record MatchInputDTO(
        @NotBlank(message = "This field must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z]+", message = "Must consist of letters only.")
        @Size(min = 4, message = "Must be at least 4 letters long")
        String countryA,

        @NotBlank(message = "This field must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z]+", message = "Must consist of letters only.")
        @Size(min = 4, message = "Must be at least 4 letters long.")
        String countryB,

        @NotNull(message = "This field must be filled in.")
        LocalDateTime dateTime,

        @NotNull(message = "This field must be filled in.")
        @DecimalMin(value = "1000", message = "Must consist of 4 numbers.")
        @DecimalMax("9999")
        Integer stadiumCode,

        @NotNull(message = "This field must be filled in.")
        @Min(value = 0, message = "Must be at least 0.")
        Integer checksum,

        @NotBlank(message = "This field must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z ]+", message = "Must consist of letters an spaces only.")
        String stadiumName,

        @NotBlank(message = "This field must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z]+", message = "Must consist of letters only.")
        String city) {
}
