package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/{teamName}")
    public String showTeamOverview(@PathVariable String teamName, Model model) {
        model.addAttribute("team", teamService.getTeamByTeamName(teamName));
        return "team-overview";
    }

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        model.addAttribute("teams", teamService.getTopTenTeams());
        return "leaderboard";
    }

}
