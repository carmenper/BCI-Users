package com.cemp.bci.users.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseConfigurationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getObjectMapperTest() {
        Assertions.assertNotNull(objectMapper);
    }

}
