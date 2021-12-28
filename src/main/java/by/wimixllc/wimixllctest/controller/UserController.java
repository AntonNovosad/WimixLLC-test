package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.dto.UpdateUserRequestDto;
import by.wimixllc.wimixllctest.repository.UserRepository;
import by.wimixllc.wimixllctest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/home")
public class UserController {


    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    ResponseEntity<?> getUsers(@RequestParam Optional<Integer> page,
                               @RequestParam Optional<String> sortBy) {
        return new ResponseEntity<>(userService.getUsers(page, sortBy), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userRepository.getById(userId), HttpStatus.OK);
    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId, UpdateUserRequestDto requestDto) {
        return new ResponseEntity<>(userService.update(userId, requestDto), HttpStatus.OK);
    }
}
