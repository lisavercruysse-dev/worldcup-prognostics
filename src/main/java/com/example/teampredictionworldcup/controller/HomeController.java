package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.service.MemberService;
import com.example.teampredictionworldcup.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final TeamService teamService;
    private final MemberService memberService;

    @GetMapping()
    public String prognosticsWorldCup(Model model) {
        model.addAttribute("member", memberService.getMemberById(7));
        return "home";
    }
}
