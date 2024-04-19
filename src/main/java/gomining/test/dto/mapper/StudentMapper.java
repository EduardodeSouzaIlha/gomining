package gomining.test.dto.mapper;

import org.modelmapper.ModelMapper;

import gomining.test.dto.StudentCreateDto;
import gomining.test.dto.StudentResponseDto;
import gomining.test.entity.Student;

public class StudentMapper {
    
    public static Student toStudent(StudentCreateDto createDto){
        return new ModelMapper().map(createDto, Student.class);
    }
    public static StudentResponseDto toDto(Student student){
        return new ModelMapper().map(student, StudentResponseDto.class);
    }
}
