package com.cemp.bci.users.exception;

import com.cemp.bci.users.enums.EnumException;

public class ApplicationException extends RuntimeException
        implements GlobalException {

    private final EnumException enumException;

    public ApplicationException(EnumException enumException) {
        super(enumException.getDetail());
        this.enumException = enumException;
    }

    @Override
    public int getCodigo() {
        return this.enumException.getCodigo();
    }

    @Override
    public String getDetail() {
        return getMessage();
    }
}
