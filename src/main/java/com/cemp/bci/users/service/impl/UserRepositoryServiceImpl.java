package com.cemp.bci.users.service.impl;

import com.cemp.bci.users.entity.InputEntity;
import com.cemp.bci.users.repository.UserRepository;
import com.cemp.bci.users.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public InputEntity getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public InputEntity createUserEntity(InputEntity user) {
        user.setCreated(getDateTime());
        user.setActive(true);
        userRepository.save(user);
        return userRepository.findByEmail(user.getEmail());
    }

    @Override
    public InputEntity updateUserEntity(InputEntity user) {
        user.setLastLogin(getDateTime());
        userRepository.save(user);
        return userRepository.findByEmail(user.getEmail());
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

}
