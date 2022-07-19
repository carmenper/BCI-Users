package com.cemp.bci.users.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DtoTest {

    @Test
    void getErrorResponseTest() {
        ErrorResponse errorResponse1 = new ErrorResponse(1, "TimeStamp");
        Assertions.assertNotNull(errorResponse1);
        Assertions.assertEquals(1, errorResponse1.getCodigo());
        Assertions.assertEquals("Error sin detalle", errorResponse1.getDetail());
        Assertions.assertEquals("TimeStamp", errorResponse1.getTimestamp());

        ErrorResponse errorResponse2 = new ErrorResponse(1, "Detail" , "TimeStamp");
        Assertions.assertNotNull(errorResponse2);
        Assertions.assertEquals(1, errorResponse2.getCodigo());
        Assertions.assertEquals("Detail", errorResponse2.getDetail());
        Assertions.assertEquals("TimeStamp", errorResponse2.getTimestamp());

    }

    @Test
    void getUserErrorResponse() {
        UserErrorResponse userErrorResponse = new UserErrorResponse(1, "detail");

        Assertions.assertNotNull(userErrorResponse);
        Assertions.assertNotNull(userErrorResponse.getError());
        Assertions.assertEquals(1, userErrorResponse.getError().size());
        Assertions.assertEquals("detail", userErrorResponse.getError().get(0).getDetail());
    }
}
