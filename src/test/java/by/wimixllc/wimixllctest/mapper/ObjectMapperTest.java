package by.wimixllc.wimixllctest.mapper;

import by.wimixllc.wimixllctest.ObjectCreator;
import by.wimixllc.wimixllctest.dto.RegistrationResponseDto;
import by.wimixllc.wimixllctest.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ObjectMapperTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ObjectCreator objectCreator;

    @Test
    void toUserResponse(){
        final User user = objectCreator.user();
        final RegistrationResponseDto responseDto = objectMapper.toUserResponse(user);
        assertEquals(user.getEmail(), responseDto.getEmailDto());
        assertEquals(user.getFirstName(), responseDto.getFirstNameDto());
        assertEquals(user.getLastName(), responseDto.getLastNameDto());
        assertEquals(user.getPhoneNumber(), responseDto.getPhoneDto());
        assertEquals(user.getUserRoles().size(), responseDto.getRolesDto().size());
    }
}
