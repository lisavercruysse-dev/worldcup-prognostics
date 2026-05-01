package com.example.teampredictionworldcup.advice;

import com.example.teampredictionworldcup.controller.MatchController;
import com.example.teampredictionworldcup.validator.MatchValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(assignableTypes = MatchController.class)
@RequiredArgsConstructor
public class MatchValidatorAdvice {

    private final MatchValidator matchValidator;

    @InitBinder("matchInputDTO")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(matchValidator);
    }
}
