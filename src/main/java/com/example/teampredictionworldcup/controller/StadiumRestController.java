package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/stadiums")
public class StadiumRestController {
    private final StadiumService stadiumService;

    @GetMapping(value = "/{id}/capacity")
    public Integer getCapacityByStadiumId(@PathVariable Integer id) {
        return stadiumService.getCapacityByStadiumId(id);
    }
}
