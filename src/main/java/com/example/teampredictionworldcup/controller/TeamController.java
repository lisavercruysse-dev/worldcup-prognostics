package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.dto.response.JoinTeamInputDTO;
import com.example.teampredictionworldcup.dto.response.TeamInputDTO;
import com.example.teampredictionworldcup.service.MemberService;
import com.example.teampredictionworldcup.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("inputDTO", new TeamInputDTO(null, memberId));
        return "createTeamForm";
    }

    @GetMapping("/{memberId}/join")
    public String showJoinTeamForm(@PathVariable int memberId, Model model) {
        model.addAttribute("member", memberService.getMemberById(memberId));
        model.addAttribute("inputDTO", new JoinTeamInputDTO(null, null, memberId));
        return "joinTeamForm";
    }

    @PostMapping("/{memberId}/{teamName}/invite")
    public String generateInviteCode(@PathVariable String teamName, @PathVariable int memberId) {
        teamService.genereateInviteCode(teamName);
        return "redirect:/teams/" + memberId + "/" + teamName;
    }

    @PostMapping
    public String makeTeam(TeamInputDTO teamInputDTO) {
        teamService.save(teamInputDTO);
        return "redirect:/teams/" + teamInputDTO.ownerId() + "/" + teamInputDTO.teamName();
    }

    @PostMapping("/members")
    public String addMember(JoinTeamInputDTO joinTeamInputDTO) {
        teamService.addMember(joinTeamInputDTO);
        return "redirect:/teams/" + joinTeamInputDTO.memberId() + "/" + joinTeamInputDTO.teamName();
    }
}
