package com.vackosar.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(value = {METHOD, FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
    String message() default "{com.vackosar.validation.Email.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
