package com.cemp.bci.users.util;

import com.cemp.bci.users.enums.EnumException;
import com.cemp.bci.users.exception.ApplicationException;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;

@Component
public class InitVectorUtil {

    private static final int POSITION_FACTOR = 3;

    public String getInitVectorString(byte[] riv) {
        if (riv == null || riv.length <= 0) {
            throw new ApplicationException(EnumException.VECTOR_EXCEPTION);
        }
        String rivString = Base64.getEncoder().encodeToString(riv);
        String rivLen = String.join("", Collections.nCopies(POSITION_FACTOR, "0"))
                + rivString.length();
        rivLen = rivLen.substring(rivLen.length() - POSITION_FACTOR);
        return rivLen + rivString;
    }

    public byte[] getEncryptedBytes(String encrypted) {
        if (encrypted == null || encrypted.trim().isEmpty()) {
            throw new ApplicationException(EnumException.ENCRYPTED_EXCEPTION);
        }
        return Base64.getDecoder()
                .decode(encrypted
                        .substring(getInitVectorLength(encrypted)
                                + POSITION_FACTOR));
    }

    public byte[] getInitVectorBytes(String encrypted) {
        if (encrypted == null || encrypted.trim().isEmpty()) {
            throw new ApplicationException(EnumException.ENCRYPTED_EXCEPTION);
        }
        String rivString = encrypted.substring(POSITION_FACTOR,
                getInitVectorLength(encrypted) + POSITION_FACTOR);
        return Base64.getDecoder().decode(rivString);
    }

    public int getInitVectorLength(String encrypted) {
        if (encrypted.length() < POSITION_FACTOR) {
            throw new ApplicationException(EnumException.ENCRYPTED_EXCEPTION);
        }
        int vectorLength = 0;
        try {
            vectorLength = Integer.parseInt(encrypted.substring(0, POSITION_FACTOR));
        } catch (NumberFormatException e) {
            throw new ApplicationException(EnumException.ENCRYPTED_FORMAT_EXCEPTION);
        }
        return vectorLength;
    }
}
