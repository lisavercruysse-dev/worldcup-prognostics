package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.MemberMinimalDTO;
import com.example.teampredictionworldcup.dto.response.TeamMinimalDTO;
import com.example.teampredictionworldcup.model.Team;
import com.example.teampredictionworldcup.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.teampredictionworldcup.dto.response.TeamOverviewDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<TeamMinimalDTO> getAllTeams() {
        List<Team> lijstTeams = teamRepository.findAll();
        return lijstTeams.stream().map(t -> new TeamMinimalDTO(t.getTeamName(), t.getScore(), t.getMembers().size())).toList();
    }

    public TeamOverviewDTO getTeamByTeamName(String teamName) {
        Team team = teamRepository.findById(teamName).orElse(null);
        if (team != null) {
            return new TeamOverviewDTO(team.getTeamName(), team.getOwner().getName(),
                    team.getMembers().stream()
                            .map(m -> new MemberMinimalDTO(m.getName(), m.getScore()))
                            .toList(), team.getScore());
        }
        return null;
    }

    public List<TeamMinimalDTO> getTopTenTeams() {
        List<Team> teams = teamRepository.findTop10ByOrderByScoreDesc();

        return teams.stream().map(t -> new TeamMinimalDTO(t.getTeamName(), t.getScore(), t.getMembers().size())).toList();
    }
}
