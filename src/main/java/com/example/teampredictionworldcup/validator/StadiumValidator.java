package com.example.teampredictionworldcup.validator;

import com.example.teampredictionworldcup.dto.response.StadiumInputDTO;
import com.example.teampredictionworldcup.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("stadiumValidator")
@RequiredArgsConstructor
public class StadiumValidator implements Validator {
    private final StadiumService stadiumService;

    @Override
    public boolean supports(Class<?> klass) {
        return StadiumInputDTO.class.isAssignableFrom(klass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StadiumInputDTO dto =  (StadiumInputDTO) target;

        if (stadiumService.getById(Integer.parseInt(dto.code())) != null) {
            errors.rejectValue("code", "duplicate.code", "Stadium already exists");
        }
        else if (Integer.parseInt(dto.code()) % 97 != dto.checksum()) {
            errors.rejectValue("code", "invalid.code", "Stadium code is invalid");
        }
    }
}
