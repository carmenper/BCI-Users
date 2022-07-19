package com.cemp.bci.users.service;

public interface CryptoService {

    String encrypt(final String unencrypted);
    String decrypt(String encrypted);
}
