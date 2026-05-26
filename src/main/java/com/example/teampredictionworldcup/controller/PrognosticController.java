package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.dto.response.MatchMinimalDTO;
import com.example.teampredictionworldcup.dto.response.MemberDTO;
import com.example.teampredictionworldcup.dto.response.PrognosticDTO;
import com.example.teampredictionworldcup.dto.response.PrognosticInputDTO;
import com.example.teampredictionworldcup.model.Prognostic;
import com.example.teampredictionworldcup.repository.MemberRepository;
import com.example.teampredictionworldcup.service.MatchService;
import com.example.teampredictionworldcup.service.MemberService;
import com.example.teampredictionworldcup.service.PrognosticService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prognostics")
public class PrognosticController {
    private final PrognosticService prognosticService;
    private final MemberService memberService;

    @GetMapping("/form/{matchId}")
    public String showForm(@PathVariable int matchId, Principal principal, Model model) {
        MemberDTO member = null;
        PrognosticDTO prognostic = null;
        if (principal != null) {
            member = memberService.getMemberByName(principal.getName());
            prognostic = prognosticService.getByMatchAndMemberId(matchId, member.id());
        }

        PrognosticInputDTO inputDTO = prognostic != null
                ? new PrognosticInputDTO(prognostic.goalsTeamA(), prognostic.goalsTeamB(), matchId, member.id())
                : new PrognosticInputDTO(null, null, matchId, member.id());

        model.addAttribute("inputDTO", inputDTO);
        return "prognosticForm";
    }

    @GetMapping()
    public String showPrognosticsForUser(Principal principal, Model model) {
        MemberDTO member = null;
        if (principal != null) {
            member = memberService.getMemberByName(principal.getName());
        }
        model.addAttribute("prognostics", prognosticService.getByUserId(member.id()));
        return "myPrognostics";
    }

    @PostMapping
    public String processForm(@Valid @ModelAttribute("inputDTO") PrognosticInputDTO inputDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "prognosticForm";
        }

        prognosticService.save(inputDTO);
        return "redirect:/matches/" + inputDTO.matchId();
    }
}
