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

import java.util.List;

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
            return new PrognosticDTO(prognostic.getId(), new MatchMinimalDTO(prognostic.getMatch().getDateTime(), prognostic.getMatch().getCountryA(), prognostic.getMatch().getCountryB()),
                    prognostic.getMember().getName(), prognostic.getGoalsTeamA(), prognostic.getGoalsTeamB());
        }
        return null;
    }

    public PrognosticDTO getById(int id) {
        Prognostic prognostic = prognosticRepository.findById(id).orElse(null);
        if (prognostic != null) {
            return new PrognosticDTO(prognostic.getId(), new MatchMinimalDTO(prognostic.getMatch().getDateTime(), prognostic.getMatch().getCountryA(), prognostic.getMatch().getCountryB()),
                    prognostic.getMember().getName(), prognostic.getGoalsTeamA(), prognostic.getGoalsTeamB());
        }
        return null;
    }

    public List<PrognosticDTO> getByUserId(int userId) {
        List<Prognostic> prognostics = prognosticRepository.findByMemberId(userId);
        return prognostics.stream().map(p -> new PrognosticDTO(p.getId(), new MatchMinimalDTO(p.getMatch().getDateTime(), p.getMatch().getCountryA(), p.getMatch().getCountryB()),
                p.getMember().getName(), p.getGoalsTeamA(), p.getGoalsTeamB())).toList();
    }

    public void save(PrognosticInputDTO inputDTO) {
        Match match = matchRepository.findById(inputDTO.matchId()).orElse(null);
        Member member = memberRepository.findById(inputDTO.memberId()).orElse(null);

        if(match != null && member != null) {
            Prognostic existing = prognosticRepository.findByMatchIdAndMemberId(match.getId(), member.getId()).orElse(null);
            if (existing == null) {
                Prognostic prognostic = new Prognostic(match, member, inputDTO.goalsTeamA(), inputDTO.goalsTeamB());
                prognosticRepository.save(prognostic);
            }
            else {
                existing.setGoalsTeamA(inputDTO.goalsTeamA());
                existing.setGoalsTeamB(inputDTO.goalsTeamB());
                prognosticRepository.save(existing);
            }
        }
    }
}
