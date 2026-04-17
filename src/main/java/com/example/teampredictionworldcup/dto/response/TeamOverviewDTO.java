package com.example.teampredictionworldcup.dto.response;

import java.util.List;

public record TeamOverviewDTO(String teamName, String inviteCode, String owner, List<MemberMinimalDTO> members, int score) {
}
