package com.example.teampredictionworldcup.controller;

import com.example.teampredictionworldcup.model.Match;
import com.example.teampredictionworldcup.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.example.teampredictionworldcup.utils.DateTimeFormats.DATE_FORMATTER;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/matches")
public class MatchRestController {

    private final MatchService matchService;

    @GetMapping(value = "/{date}")
    public List<Match> getMatchesByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);
        return matchService.getMatchesByDate(localDate);
    }
}
