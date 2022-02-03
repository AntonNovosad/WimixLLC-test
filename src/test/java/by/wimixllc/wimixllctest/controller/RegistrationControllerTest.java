package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.ObjectCreator;
import by.wimixllc.wimixllctest.dto.RegistrationRequestDto;
import by.wimixllc.wimixllctest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static by.wimixllc.wimixllctest.TextConstant.USER_EMAIL;
import static by.wimixllc.wimixllctest.TextConstant.USER_PHONE_NUMBER;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectCreator objectCreator;
    @Autowired
    private UserService userService;

    @Test
    void registrationUser() throws Exception {
        when(userService.saveUser(any(RegistrationRequestDto.class))).thenReturn(objectCreator.registerUserResponse());

        this.mockMvc
                .perform(
                        post("/home/reg")
                                .contentType(APPLICATION_JSON)
                                .content(objectCreator.toJson(objectCreator.userCreateRequest()))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(USER_EMAIL))
                .andExpect(jsonPath("$.phoneNumber").value(USER_PHONE_NUMBER));

    }
}
