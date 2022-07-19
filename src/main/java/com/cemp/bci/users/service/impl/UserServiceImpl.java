package com.cemp.bci.users.service.impl;

import com.cemp.bci.users.entity.InputEntity;
import com.cemp.bci.users.enums.EnumException;
import com.cemp.bci.users.exception.ApplicationException;
import com.cemp.bci.users.exception.ConflictException;
import com.cemp.bci.users.exception.NotFoundException;
import com.cemp.bci.users.service.CryptoService;
import com.cemp.bci.users.service.TokenService;
import com.cemp.bci.users.service.UserRepositoryService;
import com.cemp.bci.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryService repository;

    private final TokenService tokenService;

    private final CryptoService cryptoService;

    @Override
    public InputEntity createUser(InputEntity user) {
        InputEntity userEntity = repository.getUserEntityByEmail(user.getEmail());
        if (userEntity != null) {
            throw new ConflictException(EnumException.DUPLICATE_EXCEPTION);
        }

        String unencrypted = user.getPassword();

        user.setPassword(cryptoService.encrypt(unencrypted));
        user.setToken(tokenService.refreshToken(user.getEmail()));
        user = repository.createUserEntity(user);
        user.setPassword(unencrypted);

        return user;

    }

    @Override
    public InputEntity loginUser(String token) {
        String email = tokenService.getEmailFromToken(token);
        if (email == null || email.trim().isEmpty()) {
            throw new ApplicationException(EnumException.EXTRACTION_EXCEPTION);
        }
        InputEntity userEntity = repository.getUserEntityByEmail(email);
        if (userEntity == null) {
            throw new NotFoundException();
        }

        String refreshToken = tokenService.refreshToken(userEntity.getEmail());
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw new ApplicationException(EnumException.REFRESH_EXCEPTION);
        }

        userEntity.setToken(refreshToken);

        repository.updateUserEntity(userEntity);

        userEntity.setPassword(cryptoService.decrypt(userEntity.getPassword()));

        return userEntity;

    }

}
