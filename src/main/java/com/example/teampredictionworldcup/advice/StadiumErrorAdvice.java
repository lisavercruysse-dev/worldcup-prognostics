package com.example.teampredictionworldcup.advice;

import com.example.teampredictionworldcup.dto.response.ErrorResponse;
import com.example.teampredictionworldcup.exceptions.StadiumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class StadiumErrorAdvice {

    @ExceptionHandler(StadiumNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse stadiumNotFoundHandler(StadiumNotFoundException ex) {
        return new ErrorResponse(404, ex.getMessage(), LocalDateTime.now().toString());
    }
}
