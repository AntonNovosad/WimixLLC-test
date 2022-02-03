package by.wimixllc.wimixllctest;

import by.wimixllc.wimixllctest.dto.RegistrationRequestDto;
import by.wimixllc.wimixllctest.dto.RegistrationResponseDto;
import by.wimixllc.wimixllctest.entity.Role;
import by.wimixllc.wimixllctest.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

import static by.wimixllc.wimixllctest.TextConstant.*;

@Component
@RequiredArgsConstructor
public class ObjectCreator {
    @Value("${role.user}")
    private String userRole;
    @Value("${role.admin}")
    private String adminRole;
    @Value("${jwt.prefix}")
    private String prefix;

    private final PasswordEncoder passwordEncoder;

    public User user() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1l, userRole));
        return User
                .builder()
                .id(USER_ID)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .firstName(FIRST_USER_NAME)
                .lastName(LAST_USER_NAME)
                .phoneNumber(USER_PHONE_NUMBER)
                .userRoles(roles)
                .build();
    }

    public RegistrationRequestDto userCreateRequest() {
        return RegistrationRequestDto
                .builder()
                .emailDto(USER_EMAIL)
                .passwordDto(USER_PASSWORD)
                .firstNameDto(FIRST_USER_NAME)
                .lastNameDto(LAST_USER_NAME)
                .phoneDto(USER_PHONE_NUMBER)
                .build();
    }

    public RegistrationResponseDto registerUserResponse() {
        return RegistrationResponseDto
                .builder()
                .emailDto(USER_EMAIL)
                .firstNameDto(FIRST_USER_NAME)
                .lastNameDto(LAST_USER_NAME)
                .phoneDto(USER_PHONE_NUMBER)
                .rolesDto(List.of(userRole))
                .build();
    }

    public String toJson(Object o) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }
}
