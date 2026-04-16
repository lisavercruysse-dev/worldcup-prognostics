package com.example.teampredictionworldcup.service;

import com.example.teampredictionworldcup.dto.response.MatchMinimalDTO;
import com.example.teampredictionworldcup.dto.response.PrognosticDTO;
import com.example.teampredictionworldcup.dto.response.PrognosticInputDTO;
import com.example.teampredictionworldcup.model.Match;
import com.example.teampredictionworldcup.model.Member;
import com.example.teampredictionworldcup.model.Prognostic;
import com.example.teampredictionworldcup.repository.MatchRepository;
import com.example.teampredictionworldcup.repository.MemberRepository;
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
    private final MatchRepository matchRepository;
    private final MemberRepository memberRepository;

    public PrognosticDTO getByMatchAndMemberId(int matchId, int memberId) {
        Prognostic prognostic = prognosticRepository.findByMatchIdAndMemberId(matchId, memberId).orElse(null);
        if (prognostic != null) {
            return new PrognosticDTO(prognostic.getId(), new MatchMinimalDTO(prognostic.getMatch().getCountryA(), prognostic.getMatch().getCountryB()),
                    prognostic.getMember().getName(), prognostic.getGoalsTeamA(), prognostic.getGoalsTeamB());
        }
        return null;
    }

    public PrognosticDTO getById(int id) {
        Prognostic prognostic = prognosticRepository.findById(id).orElse(null);
        if (prognostic != null) {
            return new PrognosticDTO(prognostic.getId(), new MatchMinimalDTO(prognostic.getMatch().getCountryA(), prognostic.getMatch().getCountryB()),
                    prognostic.getMember().getName(), prognostic.getGoalsTeamA(), prognostic.getGoalsTeamB());
        }
        return null;
    }

    public void save(PrognosticInputDTO inputDTO) {
        Match match = matchRepository.findById(inputDTO.matchId()).orElse(null);
        Member member = memberRepository.findById(inputDTO.memberId()).orElse(null);

        if(match != null && member != null) {
            Prognostic prognostic = new Prognostic(match, member, inputDTO.goalsTeamA(), inputDTO.goalsTeamB());
            prognosticRepository.save(prognostic);
        }
    }
}
