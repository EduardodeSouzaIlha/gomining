package gomining.test.entity;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "student")
public class Student {
    
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String cpf;
    @NotBlank
    private String number;

    @NotBlank
    @Email
    private String email;

    private Role role = Role.ROLE_STUDENT;
    

    //Add data criação
    private Date createdAt;
    private Date modifiedAt;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Valid
    private List<ActivityGrade> activitiesAndGrades;


    public enum Role{
        ROLE_ADMIN, ROLE_STUDENT
    }

}
