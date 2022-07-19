package com.cemp.bci.users.util;

import com.cemp.bci.users.exception.ApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

class InitVectorUtilTest {

    private final InitVectorUtil initVectorUtil = new InitVectorUtil();
    private final SecureRandom secureRandom = new SecureRandom();

    @Test
    void getInitVectorStringTest() {
        byte[] riv = new byte[16];
        secureRandom.nextBytes(riv);

        String ivs = initVectorUtil.getInitVectorString(riv);
        Assertions.assertNotNull(ivs);
        Assertions.assertTrue(ivs.length() > 0);
    }

    @Test
    void getInitVectorStringVectorExceptionZeroLengthTest() {
        byte[] riv = new byte[0];
        secureRandom.nextBytes(riv);

        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getInitVectorString(riv);
        });

    }

    @Test
    void getInitVectorStringNullTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getInitVectorString(null);
        });
    }

    @Test
    void getEncryptedBytesTest() {
        String encrypted = "024bXxD5oGVc+MUPTp8fU+d/A==OOcNeABwNZ9HhP/cE0xRqg==";
        byte[] eb = initVectorUtil.getEncryptedBytes(encrypted);
        Assertions.assertNotNull(eb);
        Assertions.assertTrue(eb.length > 0);
    }

    @Test
    void getEncryptedBytesNullTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getEncryptedBytes(null);
        });
    }

    @Test
    void getEncryptedBytesEmptyTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getEncryptedBytes("");
        });
    }

    @Test
    void getEncryptedBytesRandomStringTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getEncryptedBytes("null");
        });
    }

    @Test
    void getInitVectorBytesTest() {
        String encrypted = "024bXxD5oGVc+MUPTp8fU+d/A==OOcNeABwNZ9HhP/cE0xRqg==";
        byte[] ivb = initVectorUtil.getInitVectorBytes(encrypted);
        Assertions.assertNotNull(ivb);
        Assertions.assertTrue(ivb.length > 0);
    }

    @Test
    void getInitVectorBytesNullTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getInitVectorBytes(null);
        });
    }

    @Test
    void getInitVectorBytesEmptyTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getInitVectorBytes("");
        });
    }

    @Test
    void getInitVectorBytesRandomStringTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getInitVectorBytes("null");
        });
    }

    @Test
    void getEncryptedLengthTest() {
        String encrypted = "024bXxD5oGVc+MUPTp8fU+d/A==OOcNeABwNZ9HhP/cE0xRqg==";
        int l = initVectorUtil.getInitVectorLength(encrypted);
        Assertions.assertTrue(l > 0);
    }

    @Test
    void getEncryptedLengthEncryptedFormatExceptionTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getInitVectorLength("null");
        });
    }

    @Test
    void getEncryptedLengthNullPointerExceptionNullTest() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            initVectorUtil.getInitVectorLength(null);
        });
    }

    @Test
    void getEncryptedLengthEncryptedExceptionEmptyTest() {
        Assertions.assertThrows(ApplicationException.class, () -> {
            initVectorUtil.getInitVectorLength("");
        });
    }

}
