package gomining.test.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentLoginDto {
    @JsonProperty("name")
    @NotBlank
    private String name;
    @JsonProperty("password")
    @NotBlank
    private String password;
    @JsonProperty("cpf")
    @NotBlank
    private String cpf;
    @JsonProperty("number")
    @NotBlank
    private String number;
    @JsonProperty("email")
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
}
