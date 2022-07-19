package com.cemp.bci.users.util;

import com.cemp.bci.users.exception.ApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

class CipherUtilTest {

    private final CipherUtil cipherUtil = new CipherUtil();

    private final SecretKeyUtil secretKeyUtil = new SecretKeyUtil();

    private final InitVectorUtil initVectorUtil = new InitVectorUtil();
    private final SecureRandom secureRandom = new SecureRandom();

    @Test
    void cipherTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");

        String unencrypted = "Mypass12";

        byte[] riv = new byte[16];
        secureRandom.nextBytes(riv);

        byte[] ce = cipherUtil.cipher(
                unencrypted.getBytes(StandardCharsets.UTF_8),
                "AES/CBC/PKCS5Padding",
                Cipher.ENCRYPT_MODE,
                new IvParameterSpec(riv),
                secretKeyUtil.getSecretKey()
        );

        String encrypted = initVectorUtil.getInitVectorString(riv)
                + Base64.getEncoder().encodeToString(ce);

        Assertions.assertNotNull(encrypted);

        byte[] cd = cipherUtil.cipher(
                initVectorUtil.getEncryptedBytes(encrypted),
                "AES/CBC/PKCS5Padding",
                Cipher.DECRYPT_MODE,
                new IvParameterSpec(initVectorUtil.getInitVectorBytes(encrypted)),
                secretKeyUtil.getSecretKey()
        );

        String decrypted = new String(cd);
        Assertions.assertNotNull(decrypted);

        Assertions.assertEquals(unencrypted, decrypted);

    }

    @Test
    void cipherTransformationExceptionTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");

        String unencrypted = "Mypass12";

        byte[] riv = new byte[16];
        secureRandom.nextBytes(riv);

        byte[] unencryptedBytes = unencrypted.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = secretKeyUtil.getSecretKey();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(riv);

        Assertions.assertThrows(ApplicationException.class, () -> {
            byte[] ce = cipherUtil.cipher(
                    unencryptedBytes,
                    "SSSS",
                    Cipher.ENCRYPT_MODE,
                    ivParameterSpec,
                    secretKeySpec
            );
        });
    }

    @Test
    void cipherModeExceptionTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");

        String unencrypted = "Mypass12";

        byte[] riv = new byte[16];
        secureRandom.nextBytes(riv);

        byte[] unencryptedBytes = unencrypted.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = secretKeyUtil.getSecretKey();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(riv);

        Assertions.assertThrows(ApplicationException.class, () -> {
            byte[] ce = cipherUtil.cipher(
                    unencryptedBytes,
                    "AES/CBC/PKCS5Padding",
                    15,
                    ivParameterSpec,
                    secretKeySpec
            );
        });
    }

    @Test
    void cipherIvExceptionTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");

        String unencrypted = "Mypass12";

        byte[] riv = new byte[16];
        secureRandom.nextBytes(riv);

        byte[] unencryptedBytes = unencrypted.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = secretKeyUtil.getSecretKey();


        Assertions.assertThrows(ApplicationException.class, () -> {
            byte[] ce = cipherUtil.cipher(
                    unencryptedBytes,
                    "AES/CBC/PKCS5Padding",
                    Cipher.ENCRYPT_MODE,
                    null,
                    secretKeySpec
            );
        });
    }

    @Test
    void cipherSecretExceptionTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");

        String unencrypted = "Mypass12";

        byte[] riv = new byte[16];
        secureRandom.nextBytes(riv);

        byte[] unencryptedBytes = unencrypted.getBytes(StandardCharsets.UTF_8);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(riv);

        Assertions.assertThrows(ApplicationException.class, () -> {
            byte[] ce = cipherUtil.cipher(
                    unencryptedBytes,
                    "AES/CBC/PKCS5Padding",
                    Cipher.ENCRYPT_MODE,
                    ivParameterSpec,
                    null
            );
        });
    }
}
