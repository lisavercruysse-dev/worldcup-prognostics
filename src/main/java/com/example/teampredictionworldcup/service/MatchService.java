package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.MatchDTO;
import com.example.teampredictionworldcup.dto.response.MatchInputDTO;
import com.example.teampredictionworldcup.dto.response.StadiumDTO;
import com.example.teampredictionworldcup.model.Match;
import com.example.teampredictionworldcup.model.Stadium;
import com.example.teampredictionworldcup.repository.MatchRepository;
import com.example.teampredictionworldcup.repository.StadiumRepository;
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
    private final StadiumRepository stadiumRepository;

    public List<MatchDTO> getAllMatches(){
        List<Match> matches = matchRepository.findAll(Sort.by(Sort.Direction.ASC, "dateTime"));
        return matches.stream().map(m ->
                new MatchDTO(m.getId(), m.getDateTime(), new StadiumDTO(m.getStadium().getName(),
                        m.getStadium().getCity(), m.getStadium().getStadiumCode(), m.getStadium().getChecksum()), m.getCountryA(), m.getCountryB())).toList();
    }

    public MatchDTO getMatchById(int id){
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
        return new MatchDTO(match.getId(), match.getDateTime(),
                    new StadiumDTO(match.getStadium().getName(), match.getStadium().getCity(), match.getStadium().getStadiumCode(), match.getStadium().getChecksum()),
                    match.getCountryA(), match.getCountryB());
    }

    public void save(MatchInputDTO dto) {
        Stadium stadium = stadiumRepository.findById(dto.stadiumCode()).orElse(null);
        if (stadium == null) {
            stadium = new Stadium(dto.stadiumCode(), dto.stadiumName(), dto.city());
            stadiumRepository.save(stadium);
        }
        Match newMatch = new Match(dto.countryA(), dto.countryB(), dto.dateTime(), stadium);
        matchRepository.save(newMatch);
    }

}
