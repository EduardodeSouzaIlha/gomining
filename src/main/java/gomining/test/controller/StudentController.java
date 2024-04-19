package gomining.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gomining.test.dto.StudentCreateDto;
import gomining.test.dto.mapper.StudentMapper;
import gomining.test.entity.Student;
import gomining.test.service.StudentService;
import lombok.AllArgsConstructor;


import org.springframework.data.domain.Pageable;
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

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

   
    @GetMapping("{id}")
    public  ResponseEntity<?>  getStudent(@PathVariable("id") String id) {
        try{
            return ResponseEntity.ok(this.studentService.getOne(id));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }    
    }
    @GetMapping
    public ResponseEntity<?> getAllStudents(Pageable pageable) {
        //http://localhost:8080/student?page=0&size=2
        try{
            return ResponseEntity.ok(this.studentService.getAll(pageable));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentCreateDto studentCreateDto) {
        try{
            return ResponseEntity.ok(this.studentService.createStudent(StudentMapper.toStudent(studentCreateDto)));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
      
    }
    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        try{
            return ResponseEntity.ok(this.studentService.update(student));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") String id) {
        try{
            return ResponseEntity.ok(this.studentService.deleteById(id));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getStudentAvgByAllActivity/{id}")
    public ResponseEntity<?> getStudentAvgByAllActivity(@PathVariable("id") String id) {
        try{
            return ResponseEntity.ok(this.studentService.getStudentAvgByAllActivity(id));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }  
    }
    @GetMapping("/getAllStudentsAvgByActivity/{id}")
    public ResponseEntity<?> getAllStudentsAvgByActivity(@PathVariable("id") String id) {
        try{
            return ResponseEntity.ok(this.studentService.calculateAverageGradeForActivity(id));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }  
    }
    @GetMapping("/grades")
    public ResponseEntity<?> grades(
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String number){

       try{
            return ResponseEntity.ok(studentService.findGradesByCpfOrEmailOrNumber(cpf, email, number));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }  
    }    
    
}
