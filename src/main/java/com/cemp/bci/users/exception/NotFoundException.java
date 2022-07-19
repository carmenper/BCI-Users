package com.cemp.bci.users.exception;

import com.cemp.bci.users.enums.EnumException;

public class NotFoundException extends ApplicationException {

    public NotFoundException() {
        super(EnumException.NOT_FOUND_EXCEPTION);
    }

}
