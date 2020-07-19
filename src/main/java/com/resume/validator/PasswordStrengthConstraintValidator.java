package com.resume.validator;

import com.resume.annotation.constraints.PasswordStrengthConstraint;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class PasswordStrengthConstraintValidator implements ConstraintValidator<PasswordStrengthConstraint, String> {
    private int minLength;
    private int maxLength;
    private int upperCaseMinCount;
    private int digitMinCount;
    private int specialCharMinCount;
    private boolean isAllowWhitespace;

    @Override
    public void initialize(PasswordStrengthConstraint constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.upperCaseMinCount = constraintAnnotation.upperCaseMinCount();
        this.digitMinCount = constraintAnnotation.digitMinCount();
        this.specialCharMinCount = constraintAnnotation.specialCharMinCount();
        this.isAllowWhitespace = constraintAnnotation.isAllowWhitespace();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) {
            return true;
        }
        List<Rule> rules = Arrays.asList(
                new LengthRule(minLength, maxLength),
                new UppercaseCharacterRule(upperCaseMinCount),
                new DigitCharacterRule(digitMinCount),
                new SpecialCharacterRule(specialCharMinCount)
        );
        if (isAllowWhitespace) {
            rules.add(new WhitespaceRule());
        }
        PasswordValidator validator = new PasswordValidator(rules);
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        StringJoiner joiner = new StringJoiner(", ");
        for (String message : validator.getMessages(result)) {
            joiner.add(message);
        }
        
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(joiner.toString()).addConstraintViolation();
        return false;
    }
}
