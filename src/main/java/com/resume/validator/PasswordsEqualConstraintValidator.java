package com.resume.validator;

import com.resume.annotation.constraints.PasswordsEqualConstraint;
import com.resume.form.PasswordForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements ConstraintValidator<PasswordsEqualConstraint, Object> {
    @Override
    public void initialize(PasswordsEqualConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            PasswordForm form = (PasswordForm) o;
            return form.getPassword().equals(form.getConfirmPassword());
        } catch (Exception e) {
            return false;
        }

    }
}
