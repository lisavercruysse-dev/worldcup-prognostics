package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PrognosticInputDTO(
        @NotNull()
        @Min(value = 0, message = "{validation.goals.Min.message}")
        Integer goalsTeamA,
        @NotNull()
        @Min(value = 0, message = "{validation.goals.Min.message}")
        Integer goalsTeamB,
        Integer matchId,
        Integer memberId) {
}
