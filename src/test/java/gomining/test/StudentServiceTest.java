package gomining.test;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;


import gomining.test.dto.StudentCreateDto;

import gomining.test.dto.mapper.StudentMapper;

import gomining.test.entity.Student;
import gomining.test.repository.StudentRepository;
import gomining.test.service.StudentService;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class StudentServiceTest {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    private static Student sheredStudent;

    @BeforeEach
    public void setUp() {
        // Insere alguns dados de teste no banco de dados
    
        StudentCreateDto student1 = new StudentCreateDto("Eduardo", "123456", "044.654.129.54", "51995084472", "eduardo@email.com" );
        sheredStudent = studentRepository.save(StudentMapper.toStudent(student1));
        
    }



    @Test
    public void createStudent() {
        StudentCreateDto student1 = new StudentCreateDto("Alice", "12345678", "044.342.457.25", "1111111", "alice@example.com");
        Student student = studentService.createStudent(StudentMapper.toStudent(student1));

        assertThat(student).isNotNull();
        assertThat(student.getId()).isNotNull();
        assertThat(student.getEmail()).isEqualTo("alice@example.com");
        assertThat(student.getRole()).isEqualTo(Student.Role.ROLE_STUDENT);

    }
    @Test
    public void getStudent() {
        
        Student student = studentService.getOne(sheredStudent.getId());

        assertThat(student).isNotNull();
        assertThat(student.getId()).isNotNull();
        assertThat(student.getId()).isEqualTo(sheredStudent.getId());
        assertThat(student.getRole()).isEqualTo(Student.Role.ROLE_STUDENT);
        assertThat(student.getEmail()).isEqualTo(sheredStudent.getEmail());
    }



  
    @AfterAll
    public void tearDown() {
        // Deleta os dados do banco 
        studentRepository.deleteAll();
    }
}

