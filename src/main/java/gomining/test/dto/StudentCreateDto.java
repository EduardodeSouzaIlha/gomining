package gomining.test.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    private String cpf;
    @NotBlank
    private String number;
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
}
