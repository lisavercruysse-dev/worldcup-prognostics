package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.dto.response.*;
import com.example.teampredictionworldcup.model.Stadium;
import com.example.teampredictionworldcup.service.MatchService;
import com.example.teampredictionworldcup.service.MemberService;
import com.example.teampredictionworldcup.service.PrognosticService;
import com.example.teampredictionworldcup.service.StadiumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {
    private final PrognosticService prognosticService;
    private final MatchService matchService;
    private final StadiumService stadiumService;
    private final MemberService memberService;

    @GetMapping("/{matchId}")
    public String getMatchById(@PathVariable int matchId, Principal principal, Model model) {
        MatchDTO matchDTO = matchService.getMatchById(matchId);
        MemberDTO member = null;
        if (principal != null) {
            member = memberService.getMemberByName(principal.getName());
        }

        boolean canEdit = LocalDateTime.now().isBefore(LocalDateTime.of(matchDTO.date(), matchDTO.startTime()).minusMinutes(60));
        model.addAttribute("match", matchDTO);
        model.addAttribute("prognostic", member != null? prognosticService.getByMatchAndMemberId(matchId, member.id()) : null);
        model.addAttribute("canEdit", canEdit);
        return "match";
    }

    @GetMapping
    public String manageMatches(Model model) {
        model.addAttribute("matches", matchService.getAllMatches());
        model.addAttribute("now",  LocalDate.now());
        return "manageMatches";
    }

   @GetMapping("/form/{matchId}")
    public String showForm(@PathVariable int matchId, Model model) {
        MatchDTO match = matchService.getMatchById(matchId);
        List<StadiumDTO> stadiums = stadiumService.getAllStadiums();
        Stadium stadium = stadiumService.getById(match.stadiumId());

        MatchInputDTO inputDTO = new MatchInputDTO(match.id(), match.countryA(), match.countryB(), match.date(), match.startTime(), match.endTime(), stadium.getStadiumCode());

        model.addAttribute("matchInputDTO", inputDTO);
        model.addAttribute("isEdit", true);
        model.addAttribute("stadiums", stadiums);
        return "matchForm";
    }

    @GetMapping("/form")
    public String showForm(MatchInputDTO matchInputDTO, Model model) {
        List<StadiumDTO> stadiums = stadiumService.getAllStadiums();
        model.addAttribute("isEdit", false);
        model.addAttribute("stadiums", stadiums);
        return "matchForm";
    }

    @PostMapping
    public String saveMatch(@Valid MatchInputDTO matchInputDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("stadiums", stadiumService.getAllStadiums());
            model.addAttribute("isEdit", matchInputDTO.id() != null && matchInputDTO.id() != 0);
            return "matchForm";
        }
        matchService.save(matchInputDTO);
        return "redirect:/matches";
    }

    @GetMapping("/{matchId}/score")
    public String showScoreForm(@PathVariable int matchId, Model model) {
        model.addAttribute("match", matchService.getMatchById(matchId));
        ScoreDTO scoreDTO = new ScoreDTO(null, null, matchId);
        model.addAttribute("scoreDTO", scoreDTO);
        return "scoreForm";
    }

    @PostMapping("/{matchId}/score")
    public String saveScore(
            @PathVariable int matchId,
            @Valid @ModelAttribute("scoreDTO") ScoreDTO scoreDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("match", matchService.getMatchById(matchId));
            return "scoreForm";
        }

        matchService.saveScore(scoreDTO);
        return "redirect:/matches";
    }
}
