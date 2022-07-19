package com.cemp.bci.users.service;

import com.cemp.bci.users.exception.ApplicationException;
import com.cemp.bci.users.service.impl.CryptoServiceImpl;
import com.cemp.bci.users.util.CipherUtil;
import com.cemp.bci.users.util.InitVectorUtil;
import com.cemp.bci.users.util.SecretKeyUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class CryptoServiceTest {

    @InjectMocks
    private CryptoServiceImpl cryptoService;

    @Spy
    private CipherUtil cipherUtil;

    @Spy
    private InitVectorUtil initVectorUtil;

    @Spy
    private SecretKeyUtil secretKeyUtil;

    @Test
    void cryptoTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");
        String encrypted = cryptoService.encrypt("Mypass12");

        String decrypted = cryptoService.decrypt(encrypted);
        Assertions.assertEquals("Mypass12", decrypted);
    }

    @Test
    void encryptionExceptionEmptyTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");
        Assertions.assertThrows(ApplicationException.class, () -> {
            cryptoService.encrypt("");
        });
    }

    @Test
    void encryptionExceptionNullTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");
        Assertions.assertThrows(ApplicationException.class, () -> {
            cryptoService.encrypt(null);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "null"})
    void decryptionExceptionTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");
        Assertions.assertThrows(ApplicationException.class, () -> {
            cryptoService.decrypt("");
        });
    }

    @Test
    void decryptionExceptionNullTest() {
        ReflectionTestUtils.setField(secretKeyUtil, "cryptoPassword", "OC4%*52df6C25O3SHc");
        Assertions.assertThrows(ApplicationException.class, () -> {
            cryptoService.decrypt(null);
        });
    }

}
