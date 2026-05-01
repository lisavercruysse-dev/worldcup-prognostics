package com.example.teampredictionworldcup.validator;

import com.example.teampredictionworldcup.dto.response.MatchDTO;
import com.example.teampredictionworldcup.dto.response.MatchInputDTO;
import com.example.teampredictionworldcup.model.Match;
import com.example.teampredictionworldcup.service.MatchService;
import com.example.teampredictionworldcup.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.List;

@Component("matchValidator")
@RequiredArgsConstructor
public class MatchValidator implements Validator {
    private final MatchService matchService;

    @Override
    public boolean supports(Class<?> klass) {
        return MatchInputDTO.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        List<MatchDTO> matches = matchService.getAllMatches();
        MatchInputDTO dto = (MatchInputDTO) target;
        LocalDate today = LocalDate.now();

        if (dto.stadiumCode() != null) {
            if (dto.stadiumCode() % 97 != dto.checksum()) {
                errors.rejectValue("stadiumCode", "stadiumcode.incorrect","Stadium code is incorrect.");
            }
        }

        if (matches.stream().anyMatch(m ->
                m.id() != dto.id() &&
                m.date().equals(dto.date()) &&
                m.stadium().stadiumCode().equals(dto.stadiumCode()) &&
                m.startTime().isBefore(dto.endtime()) &&
                dto.starttime().isBefore(m.endTime()))) {
            errors.rejectValue("starttime", "startTime.notAvailable", "A match is already scheduled in this stadium during this time.");
        }

        if (dto.date().isBefore(today)) {
            errors.rejectValue("date", "dateTime.future", "Cannot be in the past");
        }

        if (dto.countryA().isBlank() || dto.countryB().isBlank()) return;
        if (dto.countryA().equalsIgnoreCase(dto.countryB())) {
            errors.rejectValue("countryA", "countryA.sameCountry","Countries cannot be the same.");
        }
    }
}
