package com.example.teampredictionworldcup.advice;

import com.example.teampredictionworldcup.controller.TeamController;
import com.example.teampredictionworldcup.validator.JoinTeamValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(assignableTypes = TeamController.class)
@RequiredArgsConstructor
public class JoinTeamValidatorAdvice {

    private final JoinTeamValidator joinTeamValidator;

    @InitBinder("joinTeamInputDTO")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(joinTeamValidator);
    }
}
