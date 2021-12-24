package by.wimixllc.wimixllctest.Mapper;

import by.wimixllc.wimixllctest.dto.RegistrationRequestDto;
import by.wimixllc.wimixllctest.dto.RegistrationResponseDto;
import by.wimixllc.wimixllctest.entity.Role;
import by.wimixllc.wimixllctest.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapper {

    public RegistrationResponseDto toUserResponse(User user) {
        Collection<Role> userRoles = user.getUserRoles();
        List<String> roles = userRoles.stream().map(Role::getRoleName).collect(Collectors.toList());
        return RegistrationResponseDto
                .builder()
                .emailDto(user.getEmail())
                .firstNameDto(user.getFirstName())
                .lastNameDto(user.getLastName())
                .phoneDto(user.getPhoneNumber())
                .rolesDto(roles)
                .build();
    }

    public User toUser(RegistrationRequestDto request) {
        return User
                .builder()
                .email(request.getEmailDto())
                .password(request.getPasswordDto())
                .firstName(request.getFirstNameDto())
                .lastName(request.getLastNameDto())
                .phoneNumber(request.getPhoneDto())
                .build();
    }
}
