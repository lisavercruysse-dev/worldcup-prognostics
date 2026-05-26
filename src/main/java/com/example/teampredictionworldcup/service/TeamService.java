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

    private static TeamDTO toDTO(Team t) {
        return new TeamDTO(t.getTeamName(), t.getInviteCode(), t.getOwner().getName(), t.getScore(), t.getMembers().size());
    }

    public TeamDTO getTeamFromMember(int memberId) {
        return toDTO(teamRepository.findByMembersId(memberId));
    }

    public List<TeamMinimalDTO> getAllTeams() {
        List<Team> lijstTeams = teamRepository.findAll();
        return lijstTeams.stream().map(t -> new TeamMinimalDTO(t.getTeamName(), t.getScore(), t.getMembers().size())).toList();
    }

    public List<TeamDTO> getTopTenTeams() {
        List<Team> teams = teamRepository.findTop10ByOrderByScoreDesc();

        return teams.stream().map(TeamService::toDTO).toList();
    }

    public TeamDTO getTeamByName(String teamName) {
        Team team = teamRepository.findTeamByTeamName(teamName).orElse(null);
        if (team != null) {
            return toDTO(team);
        }
        else return null;
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
            team.addMember(member);
            teamRepository.save(team);
        }
    }

    public void removeMember(Integer memberId, String teamName) {
        Member member = memberRepository.findById(memberId).orElse(null);
        Team team = teamRepository.findById(teamName).orElse(null);
        if (member != null && team != null) {
            team.removeMember(member);
            teamRepository.save(team);
        }
    }
}
