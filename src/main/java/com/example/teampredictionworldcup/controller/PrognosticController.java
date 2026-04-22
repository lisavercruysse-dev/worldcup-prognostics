package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.dto.response.MatchMinimalDTO;
import com.example.teampredictionworldcup.dto.response.PrognosticDTO;
import com.example.teampredictionworldcup.dto.response.PrognosticInputDTO;
import com.example.teampredictionworldcup.service.MatchService;
import com.example.teampredictionworldcup.service.PrognosticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prognostics")
public class PrognosticController {
    private final PrognosticService prognosticService;

    @GetMapping("/form/{matchId}/{memberId}")
    public String showForm(@PathVariable int matchId, @PathVariable int memberId, Model model) {
        model.addAttribute("inputDTO", new PrognosticInputDTO(null, null, matchId, memberId));
        return "prognosticForm";
    }

    @GetMapping("/user/{userId}")
    public String showPrognosticsForUser(@PathVariable int userId, Model model) {
        model.addAttribute("prognostics", prognosticService.getByUserId(userId));
        return "myPrognostics";
    }

    @PostMapping
    public String processForm(PrognosticInputDTO inputDTO) {
        prognosticService.save(inputDTO);
        return "redirect:/home";
    }
}
