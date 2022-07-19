package com.cemp.bci.users.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class EntityTest {

    @Test
    void getUserPhoneEntity() {
        InputEntity userEntity = new InputEntity();
        userEntity.setEmail("email");
        userEntity.setPassword("password");
        userEntity.setId("id");
        userEntity.setCreated(LocalDateTime.now());
        userEntity.setActive(true);
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setToken("date");
        userEntity.setName("name");


        userEntity.getEmail();
        userEntity.getPassword();
        userEntity.getId();
        userEntity.getCreated();
        userEntity.isActive();
        userEntity.getLastLogin();
        userEntity.getToken();
        userEntity.getName();
        userEntity.getPhones();

        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setNumber(123456789);
        phoneEntity.setCitycode(9);
        phoneEntity.setCountrycode("56");
        phoneEntity.setId(1L);

        phoneEntity.getNumber();
        phoneEntity.getCitycode();
        phoneEntity.getCountrycode();
        phoneEntity.getId();

        userEntity.addPhone(phoneEntity);
        userEntity.setPhones(null);

    }
}
