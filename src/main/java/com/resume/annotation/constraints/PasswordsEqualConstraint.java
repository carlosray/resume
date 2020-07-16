package com.resume.annotation.constraints;

import com.resume.validator.PasswordsEqualConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE})
@Constraint(validatedBy = {PasswordsEqualConstraintValidator.class})
public @interface PasswordsEqualConstraint {
    String message() default "PasswordsEqualConstraint";

    Class<? extends Payload>[] payload() default { };

    Class<?>[] groups() default { };
}
