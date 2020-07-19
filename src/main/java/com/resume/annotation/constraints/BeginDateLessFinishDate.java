package com.resume.annotation.constraints;

import com.resume.validator.BeginDateLessFinishDateConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {BeginDateLessFinishDateConstraintValidator.class})
public @interface BeginDateLessFinishDate {

    String message() default "BeginDateLessFinishDate";

    String firstFieldName();

    String secondFieldName();

    Class<? extends Payload>[] payload() default { };

    Class<?>[] groups() default { };

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List
    {
        BeginDateLessFinishDate[] value();
    }
}
