package by.wimixllc.wimixllctest.service;

import by.wimixllc.wimixllctest.dto.UpdateUserRequestDto;
import by.wimixllc.wimixllctest.exception.UserDataException;
import by.wimixllc.wimixllctest.exception.UserNotFoundException;
import by.wimixllc.wimixllctest.mapper.ObjectMapper;
import by.wimixllc.wimixllctest.dto.RegistrationRequestDto;
import by.wimixllc.wimixllctest.dto.RegistrationResponseDto;
import by.wimixllc.wimixllctest.entity.Role;
import by.wimixllc.wimixllctest.entity.User;
import by.wimixllc.wimixllctest.repository.RoleRepository;
import by.wimixllc.wimixllctest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${role.admin}")
    private String roleAdmin;
    @Value("${role.user}")
    private String roleUser;


    @Transactional
    public RegistrationResponseDto saveUser(RegistrationRequestDto request) {
        log.info("Saving new user first name:{}; email:{}", request.getFirstNameDto(), request.getEmailDto());
        User user = save(request, roleUser);
        return objectMapper.toUserResponse(user);
    }

    public User save(RegistrationRequestDto request, String roleName) {
        log.info("Save user in database");
        if (userRepository.existsByEmail(request.getEmailDto())) {
            log.error("User with this email:{} already exist", request.getEmailDto());
            throw new UserDataException("User with this email already exist");
        }
        if (userRepository.existsByPhoneNumber(request.getPhoneDto())) {
            log.error("User with this phoneNumber:{} already exist", request.getPhoneDto());
            throw new UserDataException("User with this phone number already exist");
        }
        Role role = getRoleOrThrowException(roleName);
        User user = objectMapper.toUser(request);
        user.setUserRoles(Set.of(role));
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Page<User> getUsers(Optional<Integer> page, Optional<String> sortBy) {
        return userRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        10,
                        Sort.Direction.ASC, sortBy.orElse("id")
                )
        );
    }

    public User update(Long id, UpdateUserRequestDto requestDto) {
        log.info("Update user in database");
        if (userRepository.existsById(id)) {
            User user = userRepository.getById(id);
            user.setFirstName(requestDto.getFirstNameDto());
            user.setLastName(requestDto.getLastNameDto());
            return userRepository.save(user);
        } else {
            log.error("User not found in database id:{}", id);
            throw new UserNotFoundException("User not found");
        }
    }

    private Role getRoleOrThrowException(String roleName) {
        log.info("Get role by role name {}", roleName);
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role doesn't exist"));
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
