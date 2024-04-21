package gomining.test.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

    private Date createdAt;

    private Date modifiedAt;
    
 
    @Valid
    private List<ActivityGrade> activitiesAndGrades;

    public enum Role{
        ROLE_ADMIN, ROLE_STUDENT
    }

    public Student( String name, String password, String cpf, String number, String email){
        this.name = name;
        this.password = password;
        this.cpf = cpf;
        this.number = number;
        this.email = email;
    }
}
