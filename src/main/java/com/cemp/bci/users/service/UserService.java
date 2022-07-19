package com.cemp.bci.users.service;

import com.cemp.bci.users.entity.InputEntity;

public interface UserService {

    InputEntity createUser(InputEntity user);
    InputEntity loginUser(String token);

}
