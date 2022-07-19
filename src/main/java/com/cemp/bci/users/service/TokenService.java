package com.cemp.bci.users.service;

public interface TokenService {

    String refreshToken(String email);
    String getEmailFromToken(String token);
}
