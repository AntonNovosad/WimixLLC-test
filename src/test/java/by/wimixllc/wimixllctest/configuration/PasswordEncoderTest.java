package by.wimixllc.wimixllctest.configuration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static by.wimixllc.wimixllctest.TextConstant.USER_PASSWORD;

@SpringBootTest
public class PasswordEncoderTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void passwordEncoder() {
        String encodedPassword = passwordEncoder.encode(USER_PASSWORD);
        assert(passwordEncoder.matches(USER_PASSWORD, encodedPassword));
    }
}
