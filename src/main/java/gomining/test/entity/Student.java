package gomining.test.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String cpf;
    @NotNull
    private String number;

    private String role;
    
    @NotNull
    @Email
    private String email;

    //Add data criação
    private Date createdAt;
    private Date modifiedAt;
    
    private List<ActivityGrade> activitiesAndGrades;


    public enum Role{
        ROLE_ADMIN, ROLE_STUDENT
    }

}
