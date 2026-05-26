package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.MatchDTO;
import com.example.teampredictionworldcup.dto.response.MatchInputDTO;
import com.example.teampredictionworldcup.dto.response.ScoreDTO;
import com.example.teampredictionworldcup.dto.response.StadiumDTO;
import com.example.teampredictionworldcup.model.*;
import com.example.teampredictionworldcup.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final StadiumRepository stadiumRepository;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final PrognosticRepository prognosticRepository;

    private static final int X = 5;
    private static final int Y = 3;
    private static final int B = 4;
    private static final int C = 2;

    private static MatchDTO toDTO(Match m){
        return new MatchDTO(m.getId(), m.getDate(), m.getStadium().getCity(), m.getStadium().getStadiumCode(), m.getStadium().getName(),
                m.getCountryA(), m.getCountryB(), m.getStartTime(), m.getEndTime(), m.getScoreA(), m.getScoreB());
    }

    public List<MatchDTO> getAllMatches(){
        List<Match> matches = matchRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
        return matches.stream().map(MatchService::toDTO).toList();
    }

    public MatchDTO getMatchById(int id){
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
        return toDTO(match);
    }

    public void save(MatchInputDTO dto) {
        Match match = (dto.id() != null)
                ? matchRepository.findById(dto.id()).orElse(null)
                : null;

        Stadium stadium = stadiumRepository.findById(dto.stadiumCode()).orElseThrow(() -> new IllegalArgumentException("Stadium not found with id: " + dto.id()));

        if (match == null) {
            Match newMatch = new Match(dto.countryA(), dto.countryB(), dto.date(), stadium, dto.starttime(), dto.endtime());
            matchRepository.save(newMatch);
        } else {
            match.setStadium(stadium);
            match.setDate(dto.date());
            match.setCountryA(dto.countryA());
            match.setCountryB(dto.countryB());
            match.setStartTime(dto.starttime());
            match.setEndTime(dto.endtime());
            matchRepository.save(match);
        }
    }

    public void saveScore(ScoreDTO dto) {
        Match match = matchRepository.findById(dto.matchId()).orElse(null);
        List<Team> teams = teamRepository.findAll();

        if (match != null) {
            match.setScoreA(dto.scoreTeamA());
            match.setScoreB(dto.scoreTeamB());

            calculateScore(teams, match, dto);
        }
        if (match != null){
            matchRepository.save(match);
        }
    }

    private void calculateScore(List<Team> teams, Match match, ScoreDTO dto) {
        List<Member> members;

        for (Team team : teams) {
            Member onlyExactInTeam = null;
            Member onlyWinnerCorrectInTeam = null;
            members = team.getMembers();
            for (Member member : members) {
                Prognostic p = prognosticRepository.findByMatchIdAndMemberId(match.getId(), member.getId()).orElse(null);
                if (p != null) {
                    if (p.getGoalsTeamA() == dto.scoreTeamA() && p.getGoalsTeamB() == dto.scoreTeamB()) {
                        if (onlyExactInTeam == null) {
                            onlyExactInTeam = member;
                        } else {
                            onlyExactInTeam = null;
                        }
                        member.updateScore(X);
                        team.updateScore(X);
                    } else if ((p.getGoalsTeamA() > p.getGoalsTeamB() && dto.scoreTeamA() > dto.scoreTeamB()) ||
                            (p.getGoalsTeamB() > p.getGoalsTeamA() && dto.scoreTeamB() > dto.scoreTeamA()) ||
                            (p.getGoalsTeamA() == p.getGoalsTeamB() && dto.scoreTeamA() == dto.scoreTeamB())) {
                        if (onlyWinnerCorrectInTeam == null) {
                            onlyWinnerCorrectInTeam = member;
                        } else {
                            onlyWinnerCorrectInTeam = null;
                        }
                        member.updateScore(Y);
                        team.updateScore(Y);
                    }
                    memberRepository.save(member);
                }
            }
            if (onlyWinnerCorrectInTeam != null) {
                onlyWinnerCorrectInTeam.updateScore(C);
                memberRepository.save(onlyWinnerCorrectInTeam);
                team.updateScore(C);
            }
            if (onlyExactInTeam != null) {
                onlyExactInTeam.updateScore(B);
                team.updateScore(B);
                memberRepository.save(onlyExactInTeam);
            }
            teamRepository.save(team);
        }
    }

    public List<Match> getMatchesByDate(LocalDate localDate) {
        return matchRepository.findByDate(localDate);
    }
}
