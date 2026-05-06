package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.dto.response.StadiumDTO;
import com.example.teampredictionworldcup.dto.response.StadiumInputDTO;
import com.example.teampredictionworldcup.model.Stadium;
import com.example.teampredictionworldcup.service.StadiumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stadiums")
public class StadiumController {

    private final StadiumService stadiumService;

    @GetMapping
    public String showAllStadiums(Model model) {
        List<StadiumDTO> stadiums = stadiumService.getAllStadiums();
        model.addAttribute("stadiums", stadiums);
        return "stadiums";
    }

    @GetMapping("/form")
    public String showForm(StadiumInputDTO stadiumInputDTO, Model model) {
        return "stadiumForm";
    }

    @PostMapping
    public String processForm(@Valid StadiumInputDTO stadiumInputDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "stadiumForm";
        }
        stadiumService.save(stadiumInputDTO);
        return "redirect:/home";
    }
}
