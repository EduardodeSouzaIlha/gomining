package gomining.test.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import gomining.test.entity.ActivityGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {

    private String id;
    
    private String name;

    private String cpf;
 
    private String number;

    private String email;

    private String role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ActivityGrade> activitiesAndGrades;


}
