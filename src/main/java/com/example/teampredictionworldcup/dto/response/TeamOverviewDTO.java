package com.example.teampredictionworldcup.dto.response;

import java.util.List;

public record TeamOverviewDTO(String teamName, String owner, List<MemberMinimalDTO> members, int score) {
}
