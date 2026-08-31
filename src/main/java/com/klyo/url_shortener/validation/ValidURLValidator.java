package com.klyo.url_shortener.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.URI;

public class ValidURLValidator implements ConstraintValidator<ValidURL , String> {

    @Override
    public void initialize(ValidURL constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(s == null || s.isBlank()){
            return true;
        }

        try{
            URI uri = URI.create(s);

            return uri.getScheme() != null
                    && (uri.getScheme().equalsIgnoreCase("http"))
                    || (uri.getScheme().equalsIgnoreCase("https"))
                    && uri.getHost() != null;
        } catch (Exception e){
            return false;
        }
    }
}
