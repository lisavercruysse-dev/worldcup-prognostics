package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.MatchDTO;
import com.example.teampredictionworldcup.dto.response.MatchInputDTO;
import com.example.teampredictionworldcup.dto.response.ScoreDTO;
import com.example.teampredictionworldcup.dto.response.StadiumDTO;
import com.example.teampredictionworldcup.model.Match;
import com.example.teampredictionworldcup.model.Stadium;
import com.example.teampredictionworldcup.repository.MatchRepository;
import com.example.teampredictionworldcup.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final StadiumRepository stadiumRepository;

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

        match.setScoreA(dto.scoreTeamA());
        match.setScoreB(dto.scoreTeamB());

        if (match != null){
            matchRepository.save(match);
        }
    }

}
