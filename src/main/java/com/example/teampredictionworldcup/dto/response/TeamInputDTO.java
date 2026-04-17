package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TeamInputDTO(
        @NotBlank
        @Size(min = 4)
        @Pattern(regexp = "^[A-Za-z]+$")
        String teamName,

        @NotNull
        Integer ownerId) {
}
