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

import java.util.Comparator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public String showTeamOverview(@PathVariable int memberId, Model model) {
        String teamName = memberService.getMemberById(memberId).team();
        if (teamName != null) {
            model.addAttribute("members", memberService.getMembersFromTeam(teamName)
                    .stream()
                    .sorted(Comparator.comparingInt(MemberDTO::score).reversed())
                    .toList());
            model.addAttribute("team", teamService.getTeamFromMember(memberId));
        } else {
            model.addAttribute("team", null);
            model.addAttribute("members", null);
        }

        return "team-overview";
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

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        model.addAttribute("teams", teamService.getTopTenTeams());
        return "leaderboard";
    }

    @PostMapping
    public String makeTeam(@Valid TeamInputDTO teamInputDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/teams/" + teamInputDTO.ownerId() + "/create";
        }
        teamService.save(teamInputDTO);
        return "redirect:/teams/" + teamInputDTO.ownerId();
    }

    @PostMapping("/{teamName}/invite")
    public String generateInviteCode(@PathVariable String teamName) {
        teamService.genereateInviteCode(teamName);
        return "redirect:/teams/" + 1;
    }

    @PostMapping("/members")
    public String addMember(@Valid JoinTeamInputDTO joinTeamInputDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "joinTeamForm";
        }

        teamService.addMember(joinTeamInputDTO);
        return "redirect:/teams/" + joinTeamInputDTO.memberId();
    }

    @PostMapping("/{teamName}/members/{memberId}/{ownerId}")
    public String removeMember(@PathVariable Integer memberId, @PathVariable Integer ownerId, @PathVariable String teamName) {
        teamService.removeMember(memberId, teamName);
        return "redirect:/teams/" + ownerId;
    }
}
