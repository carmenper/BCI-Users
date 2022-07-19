package com.cemp.bci.users.service;

import com.cemp.bci.users.entity.InputEntity;

public interface UserRepositoryService {

    InputEntity getUserEntityByEmail(String email);
    InputEntity createUserEntity(InputEntity user);
    InputEntity updateUserEntity(InputEntity user);
}
