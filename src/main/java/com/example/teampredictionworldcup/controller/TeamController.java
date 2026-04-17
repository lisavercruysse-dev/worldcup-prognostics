package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.dto.response.JoinTeamInputDTO;
import com.example.teampredictionworldcup.dto.response.TeamInputDTO;
import com.example.teampredictionworldcup.service.MemberService;
import com.example.teampredictionworldcup.service.TeamService;
import com.example.teampredictionworldcup.validator.JoinTeamValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final MemberService memberService;

    @GetMapping("/{memberId}/{teamName}")
    public String showTeamOverview(@PathVariable String teamName, @PathVariable int memberId, Model model) {
        model.addAttribute("team", teamService.getTeamByTeamName(teamName));
        model.addAttribute("member", memberService.getMemberById(memberId));
        return "team-overview";
    }

    @GetMapping("/{memberId}")
    public String showTeamOverviewNoTeam(@PathVariable int memberId, Model model) {
        model.addAttribute("member", memberService.getMemberById(memberId));
        return "team-overview";
    }

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        model.addAttribute("teams", teamService.getTopTenTeams());
        return "leaderboard";
    }

    @GetMapping("/{memberId}/create")
    public String showCreateTeamForm(@PathVariable int memberId, Model model) {
        model.addAttribute("teamInputDTO", new TeamInputDTO(null, memberId));
        return "createTeamForm";
    }

    @GetMapping("/{memberId}/join")
    public String showJoinTeamForm(@PathVariable int memberId, Model model) {
        model.addAttribute("member", memberService.getMemberById(memberId));
        model.addAttribute("joinTeamInputDTO", new JoinTeamInputDTO(null, null, memberId));
        return "joinTeamForm";
    }

    @PostMapping("/{memberId}/{teamName}/invite")
    public String generateInviteCode(@PathVariable String teamName, @PathVariable int memberId) {
        teamService.genereateInviteCode(teamName);
        return "redirect:/teams/" + memberId + "/" + teamName;
    }

    @PostMapping
    public String makeTeam(@Valid TeamInputDTO teamInputDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("teamInputDTO", teamInputDTO);
            return "createTeamForm";
        }
        teamService.save(teamInputDTO);
        return "redirect:/teams/" + teamInputDTO.ownerId() + "/" + teamInputDTO.teamName();
    }

    @PostMapping("/members")
    public String addMember(@Valid JoinTeamInputDTO joinTeamInputDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "joinTeamForm";
        }

        teamService.addMember(joinTeamInputDTO);
        return "redirect:/teams/" + joinTeamInputDTO.memberId() + "/" + joinTeamInputDTO.teamName();
    }
}
