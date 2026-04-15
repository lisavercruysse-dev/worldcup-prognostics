package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.MatchMinimalDTO;
import com.example.teampredictionworldcup.dto.response.PrognosticDTO;
import com.example.teampredictionworldcup.model.Prognostic;
import com.example.teampredictionworldcup.repository.PrognosticRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor()
public class PrognosticService {
    private final PrognosticRepository prognosticRepository;

    public PrognosticDTO getByMatchAndMemberId(int matchId, int memberId) {
        Prognostic prognostic = prognosticRepository.findByMatchIdAndMemberId(matchId, memberId).orElse(null);
        if (prognostic != null) {
            return new PrognosticDTO(new MatchMinimalDTO(prognostic.getMatch().getCountryA(), prognostic.getMatch().getCountryB()),
                    prognostic.getMember().getName(), prognostic.getGoalsTeamA(), prognostic.getGoalsTeamB());
        }
        return null;
    }
}
