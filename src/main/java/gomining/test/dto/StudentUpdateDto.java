package gomining.test.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    private String cpf;
    
    @NotBlank
    private String number;

    @Valid
    private List<ActivityGrade> activitiesAndGrades;
}
