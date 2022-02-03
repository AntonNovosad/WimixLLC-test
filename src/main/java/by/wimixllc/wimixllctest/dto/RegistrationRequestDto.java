package by.wimixllc.wimixllctest.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {

    @NotNull
    private String emailDto;

    @NotNull
    private String passwordDto;

    @NotNull
    private String firstNameDto;

    @NotNull
    private String lastNameDto;

    @NotNull
    private String phoneDto;
}
