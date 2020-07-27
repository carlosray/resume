package com.resume.validator;

import com.resume.annotation.constraints.Adulthood;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class AdulthoodConstraintValidator implements ConstraintValidator<Adulthood, Integer> {
    private int adulthoodAge;

    @Override
    public void initialize(Adulthood constraintAnnotation) {
        this.adulthoodAge = constraintAnnotation.adulthoodAge();
    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        if (age == null) {
            return true;
        }
        return adulthoodAge <= age;
    }

    public Integer calculateAge(
            LocalDate birthDate,
            LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
