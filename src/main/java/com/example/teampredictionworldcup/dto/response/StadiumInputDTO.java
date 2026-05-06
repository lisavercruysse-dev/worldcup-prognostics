package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.*;

public record StadiumInputDTO(
        @NotNull
        @DecimalMin(value = "1000")
        @DecimalMax(value = "9999")
        Integer code,
        Integer checksum,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z ]+", message = "Must consist of letters and spaces only.")
        String name,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z ]+", message = "Must consist of letters and spaces only.")
        String city,

        @NotNull
        @DecimalMin(value = "10000")
        @DecimalMax(value = "150000")
        Integer capacity) {}
