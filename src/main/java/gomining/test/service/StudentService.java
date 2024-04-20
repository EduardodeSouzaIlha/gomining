package gomining.test.service;


import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gomining.test.entity.ActivityGrade;
import gomining.test.entity.AverageResult;
import gomining.test.entity.Grade;
import gomining.test.entity.Student;
import gomining.test.exception.EntityNotFoundException;
import gomining.test.exception.UniqueViolationException;
import gomining.test.repository.ActivityRepository;
import gomining.test.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional
    public Student createStudent(Student student) { //Metodo deve criar um estudante apenas com o email e outros dados sem as atividades, criar obj personalizado
      
        if(studentRepository.findStudentByEmail(student.getEmail()).isPresent()){
           throw new  UniqueViolationException(String.format("{%s} already exists", student.getEmail()));
        }
        if(studentRepository.findStudentByCpf(student.getCpf()).isPresent()){
            throw new UniqueViolationException(String.format("{%s} already exists", student.getCpf()));
        };
        if(studentRepository.findStudentByNumber(student.getNumber()).isPresent()){
            throw new  UniqueViolationException(String.format("{%s} already exists", student.getNumber() ));
        }
            // student.setPassword(passwordEncoder.encode(student.getPassword()));
        student = studentRepository.save(student);
        return student; 
    }

    public Student update(Student student){
        getOne(student.getId());
        // if(studentRepository.findStudentByName(student.getName()).isPresent()){
        //     throw new  UniqueViolationException(String.format("User {%s} already exists", student.getName()));
        // }
        for(ActivityGrade activityGrade : student.getActivitiesAndGrades()){
            activityRepository.findActivityById(activityGrade.getIdActivity()).orElseThrow(()-> new  UniqueViolationException(String.format("Activity {%s} do not exist", activityGrade.getIdActivity())));
        }
        student = studentRepository.save(student);
        return student; 
    }

    @Transactional(readOnly = true)
    public Student getOne(String id){
       return studentRepository.findStudentById(id).orElseThrow(()-> new EntityNotFoundException(String.format("Student {%s} do not exist", id)));
    
    }

    @Transactional(readOnly = true)
    public Page<Student> getAllPageable(Pageable pageable){
        return studentRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Boolean deleteById(String id){
        getOne(id);
        studentRepository.deleteById(id);
        return true;
    }

    public Double getStudentAvgByAllActivity(String studentId){
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.match(org.springframework.data.mongodb.core.query.Criteria.where("_id").is(studentId)),
            Aggregation.unwind("$activitiesAndGrades"),
            Aggregation.unwind("$activitiesAndGrades.grades"),
            Aggregation.group().avg("$activitiesAndGrades.grades.grade").as("averageGrade")
        );

        AggregationResults<AverageResult> results = mongoTemplate.aggregate(aggregation, "student", AverageResult.class);
        AverageResult averageResult = results.getUniqueMappedResult();

        return averageResult != null ? averageResult.getAverageGrade() : null;
    }

    public Double calculateAverageGradeForActivity(String activityId) {

            Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("$activitiesAndGrades"),
                Aggregation.match(org.springframework.data.mongodb.core.query.Criteria.where("activitiesAndGrades.idActivity").is(activityId)),
                Aggregation.unwind("$activitiesAndGrades.grades"),
                Aggregation.group().avg("$activitiesAndGrades.grades.grade").as("averageGrade")
                );
                
                AggregationResults<AverageResult> results = mongoTemplate.aggregate(aggregation, "student", AverageResult.class);
                AverageResult averageResult = results.getUniqueMappedResult();
                
               
                return averageResult != null ? averageResult.getAverageGrade() : null;
    }
    public List<Grade> findGradesByCpfOrEmailOrNumber(String cpf, String email, String number){
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.match(
                new Criteria().orOperator(
                    Criteria.where("cpf").is(cpf),
                    Criteria.where("email").is(email),
                    Criteria.where("number").is(number)
                )
            ),
            Aggregation.limit(1),
            Aggregation.unwind("activitiesAndGrades"),
            Aggregation.unwind("activitiesAndGrades.grades"),
            Aggregation.replaceRoot().withValueOf("activitiesAndGrades.grades")
        );

        AggregationResults<Grade> results = mongoTemplate.aggregate(aggregation, "student", Grade.class);
        return results.getMappedResults();
    }


}
