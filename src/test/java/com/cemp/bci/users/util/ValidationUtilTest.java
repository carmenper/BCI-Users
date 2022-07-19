package com.cemp.bci.users.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidationUtilTest {

    @Test
    void isValidEmailTest() {
        Assertions.assertFalse(ValidationUtil.isValidEmail("email"));
        Assertions.assertFalse(ValidationUtil.isValidEmail("email@"));
        Assertions.assertFalse(ValidationUtil.isValidEmail("@email"));
        Assertions.assertFalse(ValidationUtil.isValidEmail(".@email.com"));
        Assertions.assertFalse(ValidationUtil.isValidEmail(".email@email.com"));
        Assertions.assertFalse(ValidationUtil.isValidEmail("email.@email.com"));
        Assertions.assertFalse(ValidationUtil.isValidEmail("@.com"));
        Assertions.assertFalse(ValidationUtil.isValidEmail("@cl."));
        Assertions.assertFalse(ValidationUtil.isValidEmail("@cl.c"));

        Assertions.assertTrue(ValidationUtil.isValidEmail("email@email.com"));
        Assertions.assertTrue(ValidationUtil.isValidEmail("email@email.cl"));
        Assertions.assertTrue(ValidationUtil.isValidEmail("email.email@email.cl"));
        Assertions.assertTrue(ValidationUtil.isValidEmail("email.email.email@email.com"));
    }
}
