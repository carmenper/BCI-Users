package com.cemp.bci.users.util;

import com.cemp.bci.users.enums.EnumException;
import com.cemp.bci.users.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component
public class SecretKeyUtil {

    @Value("${security.crypto.password}")
    private String cryptoPassword;

    private static final String ALGORITHM = "AES";
    private static final int KEY_LENGTH = 16;
    private static final String DIGEST_ALGORITHM = "SHA-256";

    public SecretKeySpec getSecretKey() {
        if (cryptoPassword == null || cryptoPassword.trim().isEmpty()) {
            throw new ApplicationException(EnumException.CRYPTO_EXCEPTION);
        }

        MessageDigest messageDigest;
        try {
            byte[] key = cryptoPassword.getBytes(StandardCharsets.UTF_8);

            messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM);
            key = messageDigest.digest(key);
            key = Arrays.copyOf(key, KEY_LENGTH);

            return new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new ApplicationException(EnumException.CRYPTO_EXCEPTION);
        }
    }
}
