package com.example.teampredictionworldcup.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JoinTeamInputDTO(
        @NotBlank()
        String inviteCode,

        @NotBlank()
        String teamName,
        int memberId) {
}
