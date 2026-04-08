package com.example.teampredictionworldcup.dto.response;

import java.util.List;

public record TeamDTO(String teamName, String inviteCode, String owner, List<String> members, int score) {
}
