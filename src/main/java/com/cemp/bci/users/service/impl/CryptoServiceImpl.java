package com.cemp.bci.users.service.impl;

import com.cemp.bci.users.enums.EnumException;
import com.cemp.bci.users.exception.ApplicationException;
import com.cemp.bci.users.service.CryptoService;
import com.cemp.bci.users.util.CipherUtil;
import com.cemp.bci.users.util.InitVectorUtil;
import com.cemp.bci.users.util.SecretKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class CryptoServiceImpl implements CryptoService {

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    @Autowired
    private SecretKeyUtil secretKeyUtil;

    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private CipherUtil cipherUtil;

    @Autowired
    private InitVectorUtil initVectorUtil;

    @Override
    public String encrypt(final String unencrypted) {
        if(unencrypted == null || unencrypted.trim().isEmpty()) {
            throw new ApplicationException(EnumException.ENCRYPTION_EXCEPTION);
        }
        try {
            byte[] riv = new byte[16];
            secureRandom.nextBytes(riv);

            byte[] encryptedBytes = cipherUtil
                    .cipher(unencrypted.getBytes(StandardCharsets.UTF_8),
                            TRANSFORMATION,
                            Cipher.ENCRYPT_MODE,
                            new IvParameterSpec(riv),
                            secretKeyUtil.getSecretKey());

            return initVectorUtil.getInitVectorString(riv)
                    + Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException(EnumException.ENCRYPTION_EXCEPTION);
        }
    }

    @Override
    public String decrypt(String encrypted) {
        if(encrypted == null || encrypted.trim().isEmpty()) {
            throw new ApplicationException(EnumException.DECRYPTION_EXCEPTION);
        }
        try {
            byte[] decryptedBytes = cipherUtil
                    .cipher(initVectorUtil.getEncryptedBytes(encrypted),
                            TRANSFORMATION,
                            Cipher.DECRYPT_MODE,
                            new IvParameterSpec(initVectorUtil.getInitVectorBytes(encrypted)),
                            secretKeyUtil.getSecretKey());

            return new String(decryptedBytes);
        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException(EnumException.DECRYPTION_EXCEPTION);
        }
    }

}
