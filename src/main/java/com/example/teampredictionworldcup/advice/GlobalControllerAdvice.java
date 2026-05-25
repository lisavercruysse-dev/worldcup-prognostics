package com.example.teampredictionworldcup.advice;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("username")
    public String populateUsername(Authentication authentication) {
        return authentication == null ? "" : authentication.getName();
    }
}
