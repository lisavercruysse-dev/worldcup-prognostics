package com.example.teampredictionworldcup.advice;

import com.example.teampredictionworldcup.controller.TeamController;
import com.example.teampredictionworldcup.validator.CreateTeamValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(assignableTypes = TeamController.class)
@RequiredArgsConstructor
public class CreateTeamValidatorAdvice {

    private final CreateTeamValidator createTeamValidator;

    @InitBinder("teamInputDTO")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(createTeamValidator);
    }
}
