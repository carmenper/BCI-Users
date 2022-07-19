package com.cemp.bci.users.exception;

import com.cemp.bci.users.enums.EnumException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionTest {

    @Test
    void getApplicationExceptionTest() {
        try {
            throw new ApplicationException(EnumException.ENCRYPTED_EXCEPTION);
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }

    @Test
    void getBadRequestExceptionTest() {
        try {
            throw new BadRequestException(EnumException.BODY_EXCEPTION);
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }

    @Test
    void getConflictExceptionTest() {
        try {
            throw new ConflictException(EnumException.DUPLICATE_EXCEPTION);
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }

    @Test
    void getNotFoundExceptionTest() {
        try {
            throw new NotFoundException();
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }
}
