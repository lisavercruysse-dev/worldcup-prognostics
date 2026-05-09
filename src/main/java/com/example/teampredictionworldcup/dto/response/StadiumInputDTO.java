package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record StadiumInputDTO(
        @NotBlank(message = "Stadiumcode must be filled in.")
        @Pattern(regexp = "^\\d{4}$", message = "Must consist of numbers only")
        @Length(min = 4, max = 4)
        String code,

        @NotNull(message = "Checksum must be filled in.")
        @Min(value = 0, message = "Must be at least 0.")
        Integer checksum,

        @NotBlank(message = "Stadiumname must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z ]+", message = "Must consist of letters an spaces only.")
        String name,

        @NotBlank(message = "City must be filled in.")
        @Pattern(regexp =   "^[a-zA-Z]+", message = "Must consist of letters only.")
        String city,

        @NotNull
        @DecimalMin(value = "10000")
        @DecimalMax(value = "150000")
        Integer capacity) {}
