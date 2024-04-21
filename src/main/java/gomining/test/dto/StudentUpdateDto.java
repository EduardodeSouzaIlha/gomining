package gomining.test.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;

import gomining.test.entity.ActivityGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateDto {
    @Id
    private String id;

    @NotBlank
    private String name;
    
    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Invalid CPF format")
    private String cpf;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "Invalid number format")
    private String number;

    @Valid
    private List<ActivityGrade> activitiesAndGrades;

    public StudentUpdateDto(String id, String name, String cpf, String number){
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.number = number;
    }
}
