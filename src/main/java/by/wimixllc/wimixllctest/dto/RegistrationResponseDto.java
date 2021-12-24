package by.wimixllc.wimixllctest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class RegistrationResponseDto {
    private String emailDto;
    private String firstNameDto;
    private String lastNameDto;
    private String phoneDto;
    private List<String> rolesDto;
}
