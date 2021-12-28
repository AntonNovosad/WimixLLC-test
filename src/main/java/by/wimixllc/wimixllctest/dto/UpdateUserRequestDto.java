package by.wimixllc.wimixllctest.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {

    @NotNull
    private String firstNameDto;

    @NotNull
    private String lastNameDto;
}
