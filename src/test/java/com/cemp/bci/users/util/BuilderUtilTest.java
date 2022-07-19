package com.cemp.bci.users.util;

import com.cemp.bci.users.entity.InputEntity;
import com.cemp.bci.users.exception.ApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuilderUtilTest {

    @InjectMocks
    private BuilderUtil builderUtil;

    @Spy
    private ObjectMapper mapper;

    @Test
    void getUserCreationRequestTest() throws JsonProcessingException {
        String userString = "{\"name\":\"Carlos Mendoza\",\"email\":\"email@gmail.com\",\"password\":\"Mypass12\",\"phones\":[{\"number\":66104221,\"citycode\":9,\"countrycode\":\"56\"}]}";
        JsonNode userNode = mapper.readTree(userString);
        InputEntity user = builderUtil.getUser(userNode);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Carlos Mendoza", user.getName());
        Assertions.assertEquals("email@gmail.com", user.getEmail());
        Assertions.assertEquals("Mypass12", user.getPassword());
        Assertions.assertNotNull(user.getPhones());
        Assertions.assertEquals(1, user.getPhones().size());
    }

    @Test
    void getUserCreationRequestExceptionTest() throws JsonProcessingException {
        Assertions.assertThrows(ApplicationException.class, () -> {
            builderUtil.getUser(null);
        });
    }

}
