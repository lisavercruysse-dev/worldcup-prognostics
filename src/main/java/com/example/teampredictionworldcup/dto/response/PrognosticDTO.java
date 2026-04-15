package com.example.teampredictionworldcup.dto.response;

public record PrognosticDTO(MatchMinimalDTO match, String member, int goalsTeamA, int goalsTeamB) {
}
