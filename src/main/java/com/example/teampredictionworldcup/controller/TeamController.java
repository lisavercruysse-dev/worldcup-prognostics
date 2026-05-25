package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.dto.response.JoinTeamInputDTO;
import com.example.teampredictionworldcup.dto.response.MemberDTO;
import com.example.teampredictionworldcup.dto.response.TeamDTO;
import com.example.teampredictionworldcup.dto.response.TeamInputDTO;
import com.example.teampredictionworldcup.model.Member;
import com.example.teampredictionworldcup.model.Team;
import com.example.teampredictionworldcup.service.MemberService;
import com.example.teampredictionworldcup.service.TeamService;
import com.example.teampredictionworldcup.validator.JoinTeamValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final MemberService memberService;

    @GetMapping
    public String showTeamOverview(Principal principal, Model model) {
        MemberDTO member = null;
        if (principal != null) {
            member = memberService.getMemberByName(principal.getName());
        }
        String teamName = memberService.getMemberById(member.id()).team();
        if (teamName != null) {
            model.addAttribute("members", memberService.getMembersFromTeam(teamName)
                    .stream()
                    .sorted(Comparator.comparingInt(MemberDTO::score).reversed())
                    .toList());
            model.addAttribute("team", teamService.getTeamFromMember(member.id()));
        } else {
            model.addAttribute("team", null);
            model.addAttribute("members", null);
        }

        return "team-overview";
    }

    @GetMapping("create")
    public String showCreateTeamForm(Principal principal, Model model) {
        MemberDTO member = null;
        if (principal != null) {
            member = memberService.getMemberByName(principal.getName());
        }
        model.addAttribute("teamInputDTO", new TeamInputDTO(null, member.id()));
        return "createTeamForm";
    }

    @GetMapping("join")
    public String showJoinTeamForm(Principal principal, Model model) {
        MemberDTO member = null;
        if (principal != null) {
            member = memberService.getMemberByName(principal.getName());
        }
        model.addAttribute("member", memberService.getMemberById(member.id()));
        model.addAttribute("joinTeamInputDTO", new JoinTeamInputDTO(null, null, member.id()));
        return "joinTeamForm";
    }

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        model.addAttribute("teams", teamService.getTopTenTeams());
        return "leaderboard";
    }

    @PostMapping
    public String makeTeam(@Valid TeamInputDTO teamInputDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "createTeamForm";
        }
        teamService.save(teamInputDTO);
        return "redirect:/teams";
    }

    @PostMapping("/{teamName}/invite")
    public String generateInviteCode(@PathVariable String teamName) {
        teamService.genereateInviteCode(teamName);
        return "redirect:/teams";
    }

    @PostMapping("/members")
    public String addMember(@Valid JoinTeamInputDTO joinTeamInputDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "joinTeamForm";
        }

        teamService.addMember(joinTeamInputDTO);
        return "redirect:/teams";
    }

    @PostMapping("/{teamName}/members/{memberId}")
    public String removeMember(@PathVariable Integer memberId, Principal principal, @PathVariable String teamName) {
        MemberDTO owner = null;
        if (principal != null) {
            owner = memberService.getMemberByName(principal.getName());
        }
        teamService.removeMember(memberId, teamName);
        return "redirect:/teams";
    }
}
