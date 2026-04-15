package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.service.MatchService;
import com.example.teampredictionworldcup.service.PrognosticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {
    private final PrognosticService prognosticService;
    private final MatchService matchService;

    @GetMapping("/{matchId}/{memberId}")
    public String getPrognosticById(@PathVariable int matchId, @PathVariable int memberId, Model model) {
        model.addAttribute("match", matchService.getMatchById(matchId));
        model.addAttribute("prognostic", prognosticService.getByMatchAndMemberId(matchId, memberId));
        return "match";
    }
}
