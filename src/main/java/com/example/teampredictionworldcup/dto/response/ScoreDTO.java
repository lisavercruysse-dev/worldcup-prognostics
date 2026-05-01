package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.NotNull;

public record ScoreDTO(
        @NotNull
        Integer scoreTeamA,

        @NotNull
        Integer scoreTeamB,

        int matchId) {
}
