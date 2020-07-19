package com.resume.validator;

import com.resume.annotation.constraints.BeginDateLessFinishDate;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class BeginDateLessFinishDateConstraintValidator implements ConstraintValidator<BeginDateLessFinishDate, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(BeginDateLessFinishDate constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.firstFieldName();
        this.secondFieldName = constraintAnnotation.secondFieldName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        try {
            Comparable<Object> firstValue = (Comparable<Object>) BeanUtils.getPropertyDescriptor(o.getClass(), firstFieldName).getReadMethod().invoke(o);
            Comparable<Object> secondValue = (Comparable<Object>) BeanUtils.getPropertyDescriptor(o.getClass(), secondFieldName).getReadMethod().invoke(o);
            //'оба равны null' или 'первое поле не равно null и первое поле больше либо равно второму'
            isValid = firstValue == null && secondValue == null || firstValue != null && (secondValue == null || firstValue.compareTo(secondValue)<= 0);
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }
}
