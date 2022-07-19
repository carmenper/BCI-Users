package com.cemp.bci.users.controller;

import com.cemp.bci.users.entity.InputEntity;
import com.cemp.bci.users.service.UserService;
import com.cemp.bci.users.util.BuilderUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuilderUtil builderUtil;

    @MockBean
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void createTest() throws Exception {
        String userString = "{\"name\":\"Carlos Mendoza\",\"email\":\"email@gmail.com\",\"password\":\"Mypass12\",\"phones\":[{\"number\":66104221,\"citycode\":9,\"countrycode\":\"56\"}]}";
        JsonNode userNode = mapper.readTree(userString);
        InputEntity inputEntity = mapper.readValue(userNode.toString(), InputEntity.class);

        Mockito.when(builderUtil.getUser(userNode)).thenReturn(inputEntity);
        Mockito.when(userService.createUser(inputEntity)).thenReturn(inputEntity);

        this.mockMvc.perform(post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userString))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void loginTest() throws Exception {
        String userString = "{\"name\":\"Carlos Mendoza\",\"email\":\"email@gmail.com\",\"password\":\"Mypass12\",\"phones\":[{\"number\":66104221,\"citycode\":9,\"countrycode\":\"56\"}]}";
        JsonNode userNode = mapper.readTree(userString);
        InputEntity inputEntity = mapper.readValue(userNode.toString(), InputEntity.class);
        String token = "token";

        Mockito.when(userService.loginUser(token)).thenReturn(inputEntity);

        this.mockMvc.perform(get("/user/login/" + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
