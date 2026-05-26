package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ScoreDTO(
        @NotNull
        @Min(value = 0, message = "{validation.score.min}")
        Integer scoreTeamA,

        @NotNull
        @Min(value = 0, message = "{validation.score.min}")
        Integer scoreTeamB,

        int matchId) {
}
