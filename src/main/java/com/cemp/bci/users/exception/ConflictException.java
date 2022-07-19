package com.cemp.bci.users.exception;

import com.cemp.bci.users.enums.EnumException;

public class ConflictException extends RuntimeException
        implements GlobalException {

    private final EnumException enumException;

    public ConflictException(EnumException enumException) {
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
