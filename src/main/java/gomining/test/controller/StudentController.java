package gomining.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gomining.test.dto.StudentCreateDto;
import gomining.test.dto.StudentResponseDto;
import gomining.test.dto.StudentUpdateDto;
import gomining.test.dto.mapper.StudentMapper;
import gomining.test.entity.Grade;
import gomining.test.entity.Student;
import gomining.test.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "Students", description = "Here you can CRUD a student and get activity details from student")
@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("{id}")
    public  ResponseEntity<StudentResponseDto> getStudent(@PathVariable("id") String id) {
 
            Student student = this.studentService.getOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(StudentMapper.toDto(student));
 
    }
    @GetMapping("/pageable")
    public ResponseEntity<?> getAllStudents(Pageable pageable) {
        //http://localhost:8080/student?page=0&size=2

            return ResponseEntity.ok(this.studentService.getAllPageable(pageable));
 
    }
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
            List<Student> students = this.studentService.getAll();
            return ResponseEntity.ok().body(StudentMapper.toListDto(students));

    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentCreateDto studentCreateDto) {

            Student student = this.studentService.createStudent(StudentMapper.toStudent(studentCreateDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(StudentMapper.toDto(student));
      
    }
    @PutMapping
    public ResponseEntity<StudentResponseDto> updateStudent(@Valid @RequestBody StudentUpdateDto studentUpdateDto) {
            Student student = this.studentService.update(StudentMapper.updateToStudent(studentUpdateDto));
            return ResponseEntity.status(HttpStatus.OK).body(StudentMapper.toDto(student));
   
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable("id") String id) {
   
            return ResponseEntity.status(HttpStatus.OK).body(this.studentService.deleteById(id));

    }
    @GetMapping("/getStudentAvgByAllActivity/{id}")
    public ResponseEntity<Double> getStudentAvgByAllActivity(@PathVariable("id") String id) {
            return ResponseEntity.ok(this.studentService.getStudentAvgByAllActivity(id));

    }
    @GetMapping("/getAllStudentsAvgByActivity/{id}")
    public ResponseEntity<Double> getAllStudentsAvgByActivity(@PathVariable("id") String id) {

            return ResponseEntity.ok(this.studentService.calculateAverageGradeForActivity(id));

    }
    @GetMapping("/grades")
    public ResponseEntity<List<Grade>> grades(
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String number){

 
            return ResponseEntity.ok(studentService.findGradesByCpfOrEmailOrNumber(cpf, email, number));

    }    
    
}
