package com.resume.annotation.constraints;

import com.resume.validator.PasswordStrengthConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PasswordStrengthConstraintValidator.class})
public @interface PasswordStrengthConstraint {
    String message() default "PasswordStrengthConstraint";

    int minLength() default 8;

    int maxLength() default 30;

    int upperCaseMinCount() default 1;

    int digitMinCount() default 1;

    int specialCharMinCount() default 1;

    boolean isAllowWhitespace() default false;

    Class<? extends Payload>[] payload() default { };

    Class<?>[] groups() default { };
}
