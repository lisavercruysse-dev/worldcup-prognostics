package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TeamInputDTO(
        @NotBlank
        @Size(min = 4, message = "{validation.teamName.size}")
        @Pattern(regexp = "^[A-Za-z ]+$", message = "{validation.string.Pattern.lettersAndSpacesOnly}")
        String teamName,

        @NotNull
        Integer ownerId) {
}
