package com.cemp.bci.users.service;

import com.cemp.bci.users.entity.InputEntity;
import com.cemp.bci.users.entity.PhoneEntity;
import com.cemp.bci.users.repository.UserRepository;
import com.cemp.bci.users.service.impl.UserRepositoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @InjectMocks
    private UserRepositoryServiceImpl userRepositoryService;

    @Mock
    private UserRepository userRepository;

    @Test
    void createUserTest() {
        InputEntity userEntity = new InputEntity();
        userEntity.setEmail("carlosmendoza@gmail.com");
        userEntity.setPassword("password");
        userEntity.setName("Carlos Mendoza");
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setCountrycode("56");
        phoneEntity.setCitycode(9);
        phoneEntity.setNumber(123456789);
        userEntity.addPhone(phoneEntity);

        Mockito.when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(userEntity);
        InputEntity ue = userRepositoryService.createUserEntity(userEntity);

        Assertions.assertNotNull(ue);
        Assertions.assertEquals(userEntity.getEmail(), ue.getEmail());

    }

    @Test
    void updateUserTest() {
        InputEntity userEntity = new InputEntity();
        userEntity.setEmail("carlosmendoza@gmail.com");
        userEntity.setPassword("password");
        userEntity.setName("Carlos Mendoza");
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setCountrycode("56");
        phoneEntity.setCitycode(9);
        phoneEntity.setNumber(123456789);
        userEntity.addPhone(phoneEntity);

        Mockito.when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(userEntity);
        InputEntity ue = userRepositoryService.updateUserEntity(userEntity);
        Assertions.assertNotNull(ue);
        Assertions.assertEquals(userEntity.getEmail(), ue.getEmail());

    }

    @Test
    void getUserByEmailTest() {
        String email = "carlosmendoza@gmail.com";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(new InputEntity());
        InputEntity userEntity = userRepositoryService.getUserEntityByEmail(email);
        Assertions.assertNotNull(userEntity);
    }


}
