package com.gbc.codingmates.validator.validator;

import com.gbc.codingmates.validator.annotation.CustomEnum;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEnumValidator implements ConstraintValidator<CustomEnum, String> {

    private CustomEnum annotation;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (Enum enumValue : annotation.enumClass().getEnumConstants()) {
            if (enumValue.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(CustomEnum constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }
}
