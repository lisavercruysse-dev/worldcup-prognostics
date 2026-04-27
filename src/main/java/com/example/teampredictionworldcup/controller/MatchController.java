package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.dto.response.MatchDTO;
import com.example.teampredictionworldcup.dto.response.MatchInputDTO;
import com.example.teampredictionworldcup.service.MatchService;
import com.example.teampredictionworldcup.service.PrognosticService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {
    private final PrognosticService prognosticService;
    private final MatchService matchService;

    @GetMapping("/{matchId}/{memberId}")
    public String getMatchById(@PathVariable int matchId, @PathVariable int memberId, Model model) {
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("match", matchService.getMatchById(matchId));
        model.addAttribute("prognostic", prognosticService.getByMatchAndMemberId(matchId, memberId));
        model.addAttribute("now", now);
        return "match";
    }

    @GetMapping
    public String manageMatches(Model model) {
        model.addAttribute("matches", matchService.getAllMatches());
        return "manageMatches";
    }

    @GetMapping("/form/{matchId}")
    public String showForm(@PathVariable int matchId, Model model) {
        MatchDTO match = matchService.getMatchById(matchId);

        MatchInputDTO inputDTO = match != null
                ? new MatchInputDTO(match.countryA(), match.countryB(), match.date(), match.stadium().stadiumCode(), match.stadium().checksum(), match.stadium().name(), match.stadium().city())
                : new MatchInputDTO(null, null, null, 0, 0, null, null);

        model.addAttribute("matchInputDTO", inputDTO);
        model.addAttribute("isEdit", true);
        return "matchForm";
    }

    @GetMapping("/form")
    public String showForm(MatchInputDTO matchInputDTO, Model model) {
        model.addAttribute("isEdit", false);
        return "matchForm";
    }

    @PostMapping
    public String saveMatch(@Valid MatchInputDTO matchInputDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "matchForm";
        }
        matchService.save(matchInputDTO);
        return "redirect:/matches";
    }
}
