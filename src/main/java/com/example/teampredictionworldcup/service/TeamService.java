package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.model.Team;
import com.example.teampredictionworldcup.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.teampredictionworldcup.dto.response.TeamDTO;
import com.example.teampredictionworldcup.model.Member;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<TeamDTO> getAllTeams() {
        List<Team> lijstTeams = teamRepository.findAll();
        return lijstTeams.stream()
                .map(t -> new TeamDTO(t.getTeamName(), t.getInviteCode(),
                        t.getOwner().getName(), t.getMembers().stream().map(Member::getName)
                        .toList(), t.getScore()))
                .toList();

    }

    public TeamDTO getTeamByTeamName(String teamName) {
       Team team = teamRepository.findById(teamName).orElse(null);
        if (team != null) {
            return new TeamDTO(team.getTeamName(), team.getInviteCode(), team.getOwner().toString(),
                    team.getMembers().stream().map(Member::getName).toList(), team.getScore());
        }
        return null;
    }
}
