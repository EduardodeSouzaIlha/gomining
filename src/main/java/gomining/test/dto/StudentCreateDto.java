package gomining.test.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateDto {
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String cpf;
    @NotNull
    private String number;
    @NotNull
    @Email
    private String email;
}
