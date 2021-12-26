package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.entity.User;
import by.wimixllc.wimixllctest.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/home")
public class UserController {


    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    Page<User> getUsers(@RequestParam Optional<Integer> page,
                        @RequestParam Optional<String> sortBy) {
        return userRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        10,
                        Sort.Direction.ASC, sortBy.orElse("id")
                )
        );
    }
}
