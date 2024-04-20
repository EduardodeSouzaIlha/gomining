package gomining.test.jwt;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import gomining.test.entity.Student;

public class JwtUserDatails extends User {

    private Student student;

    public JwtUserDatails(Student student) {
        super(student.getEmail(), student.getPassword(), AuthorityUtils.createAuthorityList(student.getRole().name()));
        this.student = student;
    }

    public String getId(){
        return this.student.getId();
    }

    public String getRole(){
        return this.student.getRole().name();
    }
    
}
