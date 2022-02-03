package by.wimixllc.wimixllctest.repository;

import by.wimixllc.wimixllctest.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static by.wimixllc.wimixllctest.TextConstant.ADMIN_EMAIL;
import static by.wimixllc.wimixllctest.TextConstant.ADMIN_PHONE_NUMBER;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        final Optional<User> optionalUser = userRepository.findByEmail(ADMIN_EMAIL);
        System.out.println(optionalUser.toString());
        assertTrue(optionalUser.isPresent());
    }

    @Test
    void existsByEmail() {
        assertTrue(userRepository.existsByEmail(ADMIN_EMAIL));
    }

    @Test
    void existsByPhoneNumber() {
        userRepository.existsByPhoneNumber(ADMIN_PHONE_NUMBER);
    }
}
