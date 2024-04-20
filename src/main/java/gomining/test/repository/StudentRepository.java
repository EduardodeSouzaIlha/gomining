package gomining.test.repository;



import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import gomining.test.entity.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{
    
    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findStudentByNumber(String number);

    Optional<Student> findStudentByCpf(String cpf);

    Optional<Student> findStudentById(String id);

    Optional<Student> findStudentByName(String name);
}
