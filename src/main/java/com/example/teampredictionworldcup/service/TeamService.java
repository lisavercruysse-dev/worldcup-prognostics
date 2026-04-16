package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.*;
import com.example.teampredictionworldcup.model.Member;
import com.example.teampredictionworldcup.model.Team;
import com.example.teampredictionworldcup.repository.MemberRepository;
import com.example.teampredictionworldcup.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public List<TeamMinimalDTO> getAllTeams() {
        List<Team> lijstTeams = teamRepository.findAll();
        return lijstTeams.stream().map(t -> new TeamMinimalDTO(t.getTeamName(), t.getScore(), t.getMembers().size())).toList();
    }

    public TeamOverviewDTO getTeamByTeamName(String teamName) {
        Team team = teamRepository.findById(teamName).orElse(null);
        if (team != null) {
            return new TeamOverviewDTO(team.getTeamName(), team.getInviteCode(),team.getOwner().getName(),
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

    public void genereateInviteCode(String teamName) {
        Team team = teamRepository.findById(teamName).orElse(null);
        if(team != null) {
            team.setInviteCode();
            teamRepository.save(team);
        }
    }

    public void save(TeamInputDTO teamInputDTO) {
        Member member = memberRepository.findById(teamInputDTO.ownerId()).orElse(null);

        if (member != null) {
            Team team = new Team(teamInputDTO.teamName(), member);
            teamRepository.save(team);
        }
    }

    public void addMember(JoinTeamInputDTO joinTeamInputDTO) {
        Member member = memberRepository.findById(joinTeamInputDTO.memberId()).orElse(null);
        Team team = teamRepository.findById(joinTeamInputDTO.teamName()).orElse(null);
        if (member != null && team != null) {
            if (joinTeamInputDTO.inviteCode().equals(team.getInviteCode())) {
                team.addMember(member);
                teamRepository.save(team);
            }
        }
    }
}
