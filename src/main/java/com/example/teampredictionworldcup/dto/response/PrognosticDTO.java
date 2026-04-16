package com.example.teampredictionworldcup.dto.response;

public record PrognosticDTO(int id, MatchMinimalDTO match, String member, int goalsTeamA, int goalsTeamB) {
}
