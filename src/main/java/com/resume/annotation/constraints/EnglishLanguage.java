package com.resume.annotation.constraints;

import com.resume.validator.EnglishLanguageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, CONSTRUCTOR, ANNOTATION_TYPE, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EnglishLanguageValidator.class})
public @interface EnglishLanguage {
    String message() default "EnglishLanguage";
    // 0123456789
    boolean withNumbers() default true;
    //.,?!-:()'"[]{}; \t\n
    boolean withPunctuations() default true;
    //~#$%^&*-+=_\\|/@`!'\";:><,.?{}
    boolean withSpechSymbols() default true;

    Class<? extends Payload>[] payload() default { };

    Class<?>[] groups() default { };
}
