package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.dto.UpdateUserRequestDto;
import by.wimixllc.wimixllctest.repository.UserRepository;
import by.wimixllc.wimixllctest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/profile/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/listUsers")
    ResponseEntity<?> getUsers(@RequestParam Optional<Integer> page,
                               @RequestParam Optional<String> sortBy) {
        return new ResponseEntity<>(userService.getUsers(page, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId,
                                    @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        return new ResponseEntity<>(userService.update(userId, updateUserRequestDto), HttpStatus.OK);
    }
}
