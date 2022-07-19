package com.cemp.bci.users.controller;

import com.cemp.bci.users.entity.InputEntity;
import com.cemp.bci.users.enums.EnumException;
import com.cemp.bci.users.exception.ApplicationException;
import com.cemp.bci.users.exception.BadRequestException;
import com.cemp.bci.users.service.UserService;
import com.cemp.bci.users.util.BuilderUtil;
import com.cemp.bci.users.util.ValidationUtil;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private BuilderUtil builderUtil;

    private final UserService userService;

    @PostMapping(value = "/sign-up",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody JsonNode userCreationRequestJson) {

        if (userCreationRequestJson == null
                || userCreationRequestJson.isNull()
                || userCreationRequestJson.isEmpty()) {
            throw new BadRequestException(EnumException.BODY_EXCEPTION);
        }

        InputEntity inputUserEntity = builderUtil
                .getUser(userCreationRequestJson);

        validateUser(inputUserEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(inputUserEntity));
    }

    @GetMapping(value = "/login/{token}",
            produces = "application/json")
    public ResponseEntity<Object> login(@PathVariable("token") String token) {

        if (token == null
                || token.trim().isEmpty()) {
            throw new ApplicationException(EnumException.TOKEN_EXCEPTION);
        }

        return ResponseEntity
                .ok(userService.loginUser(token));
    }

    private void validateUser(InputEntity user) {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()
                || !ValidationUtil.isValidEmail(user.getEmail())) {
            throw new BadRequestException(EnumException.EMAIL_EXCEPTION);
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()
                || !ValidationUtil.isValidPassword(user.getPassword())) {
            throw new BadRequestException(EnumException.PASSWORD_EXCEPTION);
        }
    }

}
