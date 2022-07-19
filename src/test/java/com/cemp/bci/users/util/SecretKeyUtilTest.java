package com.cemp.bci.users.util;

import com.cemp.bci.users.exception.ApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.spec.SecretKeySpec;

class SecretKeyUtilTest {

    private final SecretKeyUtil secretKeyUtil = new SecretKeyUtil();

    @Test
    void getSecretKey() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");
        SecretKeySpec secretKeySpec = secretKeyUtil.getSecretKey();
        Assertions.assertNotNull(secretKeySpec);
    }

    @Test
    void getSecretKeyNullTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", null);
        Assertions.assertThrows(ApplicationException.class, () -> {
            secretKeyUtil.getSecretKey();
        });
    }

    @Test
    void getSecretKeyEmptyTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "");
        Assertions.assertThrows(ApplicationException.class, () -> {
            secretKeyUtil.getSecretKey();
        });
    }
}
