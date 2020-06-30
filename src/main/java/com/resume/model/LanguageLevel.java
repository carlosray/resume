package com.resume.model;

import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.beans.PropertyEditorSupport;

public enum  LanguageLevel {
    BEGINNER,
    ELEMENTARY,
    PRE_INTERMEDIATE,
    INTERMEDIATE,
    UPPER_INTERMEDIATE,
    ADVANCED,
    PROFICIENCY;

    public String getDbValue(){
        return name().toLowerCase();
    }

    public int getSliderIntValue(){
        return ordinal();
    }

    public String getCaption() {
        return StringUtils.capitalize(name());
    }

    @Converter
    public static class PersistJPAConverter implements AttributeConverter<LanguageLevel, String> {

        @Override
        public String convertToDatabaseColumn(LanguageLevel languageLevel) {
            return languageLevel.getDbValue();
        }

        @Override
        public LanguageLevel convertToEntityAttribute(String dbValue) {
            return LanguageLevel.valueOf(dbValue.toUpperCase());
        }

    }

    public static PropertyEditorSupport getPropertyEditor(){
        return new PropertyEditorSupport(){
            @Override
            public void setAsText(String sliderIntValue) throws IllegalArgumentException {
                setValue(LanguageLevel.values()[Integer.parseInt(sliderIntValue)]);
            }
        };
    }

}
