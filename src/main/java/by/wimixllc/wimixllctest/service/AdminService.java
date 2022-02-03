package by.wimixllc.wimixllctest.service;

import by.wimixllc.wimixllctest.entity.User;
import by.wimixllc.wimixllctest.exception.UserNotFoundException;
import by.wimixllc.wimixllctest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void delete(Long userId) {
        log.info("Delete user by id");
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            log.error("User not found");
            throw new UserNotFoundException("User not found");
        }
    }
}
