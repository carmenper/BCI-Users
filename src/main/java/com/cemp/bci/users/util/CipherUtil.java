package com.cemp.bci.users.util;

import com.cemp.bci.users.enums.EnumException;
import com.cemp.bci.users.exception.ApplicationException;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Component
public class CipherUtil {

    public byte[] cipher(byte [] input,
                         String transformation,
                         int cipherMode,
                         IvParameterSpec ivParameterSpec,
                         SecretKeySpec secretKeySpec) {
        if (input == null || input.length <= 0
                || ivParameterSpec == null || secretKeySpec == null ) {
            throw new ApplicationException(EnumException.CRYPTO_EXCEPTION);
        }
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(cipherMode, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(input);
        } catch (Exception e) {
            throw new ApplicationException(EnumException.CRYPTO_EXCEPTION);
        }
    }

}
