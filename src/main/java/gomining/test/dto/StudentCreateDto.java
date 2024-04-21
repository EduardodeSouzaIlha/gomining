package gomining.test.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateDto {
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Invalid CPF format")
    private String cpf;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "Invalid number format")
    private String number;

    @NotBlank
    @Email
    private String email;

}
