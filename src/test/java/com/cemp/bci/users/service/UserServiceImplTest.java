package com.cemp.bci.users.service;

import com.cemp.bci.users.entity.InputEntity;
import com.cemp.bci.users.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepositoryService userRepositoryService;
    @Mock
    private TokenService tokenService;
    @Mock
    private CryptoService cryptoService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void createUserTest() throws Exception {
        String userString = "{\"name\":\"Carlos Mendoza\",\"email\":\"email@gmail.com\",\"password\":\"Mypass12\",\"phones\":[{\"number\":66104221,\"citycode\":9,\"countrycode\":\"56\"}]}";
        JsonNode userNode = mapper.readTree(userString);

        InputEntity inputEntity = mapper.readValue(userNode.toString(), InputEntity.class);

        Mockito.when(userRepositoryService.getUserEntityByEmail(inputEntity.getEmail())).thenReturn(null);
        Mockito.when(cryptoService.encrypt(inputEntity.getPassword())).thenReturn("encrypted");
        Mockito.when(tokenService.refreshToken(inputEntity.getEmail())).thenReturn("token");
        Mockito.when(userRepositoryService.createUserEntity(inputEntity)).thenReturn(inputEntity);

        InputEntity user = userService.createUser(inputEntity);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Mypass12", user.getPassword());
        Assertions.assertEquals("token", user.getToken());
    }

    @Test
    void loginUserTest() throws Exception {
        String userString = "{\"name\":\"Carlos Mendoza\",\"email\":\"email@gmail.com\",\"password\":\"Mypass12\",\"phones\":[{\"number\":66104221,\"citycode\":9,\"countrycode\":\"56\"}]}";
        JsonNode userNode = mapper.readTree(userString);

        InputEntity inputEntity = mapper.readValue(userNode.toString(), InputEntity.class);

        String token = "token";

        Mockito.when(tokenService.getEmailFromToken(token)).thenReturn(inputEntity.getEmail());
        Mockito.when(userRepositoryService.getUserEntityByEmail(inputEntity.getEmail())).thenReturn(inputEntity);
        Mockito.when(tokenService.refreshToken(inputEntity.getEmail())).thenReturn(token);
        Mockito.when(cryptoService.decrypt(inputEntity.getPassword())).thenReturn("Mypass12");

        InputEntity user = userService.loginUser(token);

        Assertions.assertNotNull(user);
        Assertions.assertEquals("Mypass12", user.getPassword());
        Assertions.assertEquals("token", user.getToken());
    }
}
