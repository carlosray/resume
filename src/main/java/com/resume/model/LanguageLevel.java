package com.resume.model;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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

}
