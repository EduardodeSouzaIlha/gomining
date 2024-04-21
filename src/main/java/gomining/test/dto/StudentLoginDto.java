package gomining.test.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
    @JsonProperty("email")
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;

    @JsonProperty("password")
    @NotBlank
    private String password;


}
