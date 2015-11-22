package com.vackosar.validation;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationTest {

    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void validateInvalidEmail() {
        User user = new User("x");
        final Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void validateValidEmail() {
        User user = new User("x@b");
        final Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assert.assertEquals(0, violations.size());
    }
}
