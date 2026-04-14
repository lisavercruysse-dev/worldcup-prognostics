package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.MatchDTO;
import com.example.teampredictionworldcup.dto.response.StadiumDTO;
import com.example.teampredictionworldcup.model.Match;
import com.example.teampredictionworldcup.repository.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    public List<MatchDTO> getAllMatches(){
        List<Match> matches = matchRepository.findAll(Sort.by(Sort.Direction.ASC, "dateTime"));
        return matches.stream().map(m ->
                new MatchDTO(m.getDateTime(), new StadiumDTO(m.getStadium().getName(),
                        m.getStadium().getCity()), m.getCountryA(), m.getCountryB())).toList();
    }
}
