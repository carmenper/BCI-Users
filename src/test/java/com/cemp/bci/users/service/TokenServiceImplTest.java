package com.cemp.bci.users.service;

import com.cemp.bci.users.service.impl.TokenServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class TokenServiceImplTest {

    private final TokenServiceImpl tokenService = new TokenServiceImpl();

    @Test
    void refreshTokenTest() {
        ReflectionTestUtils.setField(tokenService, "jwtSecret",
                "SgUkXp2s5v8y/B?E(H+MbQeThWmYq3t6w9z$C&F)J@NcRfUjXn2r4u7x!A%D*G-K");
        String token = tokenService.refreshToken("carlos@gmail.com");
        System.out.println(token);
        Assertions.assertNotNull(token);
    }

    @Test
    void getEmailFromTokenTest() {
        ReflectionTestUtils.setField(tokenService, "jwtSecret",
                "SgUkXp2s5v8y/B?E(H+MbQeThWmYq3t6w9z$C&F)J@NcRfUjXn2r4u7x!A%D*G-K");
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYXJsb3NAZ21haWwuY29tIiwiaWF0IjoxNjU4MTc2MjY2fQ.9UDjE1GfLBHFiI6VPypJtOGTVUtjvtekz_QlWxPqK1fjMEdf4hIccPUK1qFOfpQd-UxumaAgZNfFf-2gB2AH6Q";
        String email = tokenService.getEmailFromToken(token);
        Assertions.assertNotNull(email);
        Assertions.assertEquals("carlos@gmail.com", email);
    }

}
