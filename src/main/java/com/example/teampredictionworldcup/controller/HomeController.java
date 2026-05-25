package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.dto.response.MemberDTO;
import com.example.teampredictionworldcup.model.Member;
import com.example.teampredictionworldcup.service.MatchService;
import com.example.teampredictionworldcup.service.MemberService;
import com.example.teampredictionworldcup.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final TeamService teamService;
    private final MemberService memberService;
    private final MatchService matchService;

    @GetMapping()
    public String prognosticsWorldCup(Model model, Principal principal) {
        MemberDTO memberDTO = null;
        if (principal != null) {
            memberDTO = memberService.getMemberByName(principal.getName());
        }

        model.addAttribute("member", memberDTO);
        model.addAttribute("matches", matchService.getAllMatches());
        return "home";
    }
}
