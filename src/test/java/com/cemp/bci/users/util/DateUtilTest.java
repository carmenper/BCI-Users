package com.cemp.bci.users.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateUtilTest {

    @Test
    void getTimeStampVoidTest() {
       String timeStamp = DateUtil.getTimeStamp();
        Assertions.assertNotNull(timeStamp);
    }

    @Test
    void getTimeStampTest() {
        String timeStamp = DateUtil.getTimeStamp(LocalDateTime.now());
        Assertions.assertNotNull(timeStamp);
    }

}
