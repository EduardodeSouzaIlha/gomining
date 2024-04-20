package gomining.test.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gomining.test.entity.Student;
import gomining.test.service.StudentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JwtUserDatailsService implements UserDetailsService{

    private final StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentService.searchByStudentEmail(email);
        return new JwtUserDatails(student);
    }

    public JwtToken getTokenAuthenticated(String name){
        Student.Role role = studentService.searchRoleByStudentName(name);
        return JwtUtils.createToken(name, role.name().substring("ROLE_".length()));
    }
    
    
}
