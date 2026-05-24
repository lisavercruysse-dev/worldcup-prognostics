package com.example.teampredictionworldcup.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class CupPeriodConstraintValidator implements ConstraintValidator<ValidDate, LocalDate> {
    private static final LocalDate STARTDATE = LocalDate.of(2026, 6, 1);
    private static final LocalDate ENDDATE = LocalDate.of(2026, 9, 1);

    @Override
    public void initialize(ValidDate constraintAnnotation) {}

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return (value.isAfter(STARTDATE) && value.isBefore(ENDDATE));
    }
}
