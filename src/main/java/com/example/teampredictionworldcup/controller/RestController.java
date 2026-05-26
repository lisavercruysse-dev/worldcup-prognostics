package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.model.Match;
import com.example.teampredictionworldcup.service.MatchService;
import com.example.teampredictionworldcup.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

import static com.example.teampredictionworldcup.utils.DateTimeFormats.DATE_FORMATTER;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class RestController {
    private final StadiumService stadiumService;
    private final MatchService matchService;

    @GetMapping(value = "/stadiums/{id}/capacity")
    public Integer getCapacityByStadiumId(@PathVariable Integer id) {
        return stadiumService.getCapacityByStadiumId(id);
    }

    @GetMapping(value = "/matches/{date}")
    public List<Match> getMatchesByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);
        return matchService.getMatchesByDate(localDate);
    }
}
