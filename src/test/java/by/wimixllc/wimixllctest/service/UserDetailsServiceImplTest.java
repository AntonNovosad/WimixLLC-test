package by.wimixllc.wimixllctest.service;

import by.wimixllc.wimixllctest.ObjectCreator;
import by.wimixllc.wimixllctest.entity.User;
import by.wimixllc.wimixllctest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static by.wimixllc.wimixllctest.TextConstant.USER_EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectCreator objectCreator;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername() {
        //mock
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.of(objectCreator.user()));
        User user = objectCreator.user();
        //call service
        UserDetails userDetails = userDetailsService.loadUserByUsername(USER_EMAIL);
        //assert
        assertEquals(user.getEmail(), userDetails.getUsername());
    }
}
