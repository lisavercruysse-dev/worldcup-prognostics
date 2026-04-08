package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public String showTeamOverview(Model model) {
        model.addAttribute("team", teamService.getTeamByTeamName("Team A"));
        return "team-overview";
    }
}
