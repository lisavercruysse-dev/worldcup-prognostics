package com.example.teampredictionworldcup.advice;

import com.example.teampredictionworldcup.controller.StadiumController;
import com.example.teampredictionworldcup.validator.StadiumValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(assignableTypes = {StadiumController.class})
@RequiredArgsConstructor
public class CreateStadiumAdvice {

    private final StadiumValidator stadiumValidator;

    @InitBinder("stadiumInputDTO")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(stadiumValidator);
    }
}
