package com.klyo.url_shortener.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidURLValidator.class)
@Target({ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidURL {

    String message() default "Invalid URL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
