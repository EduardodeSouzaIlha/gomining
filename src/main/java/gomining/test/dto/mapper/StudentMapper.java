package gomining.test.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import gomining.test.dto.StudentCreateDto;
import gomining.test.dto.StudentResponseDto;
import gomining.test.dto.StudentUpdateDto;
import gomining.test.entity.Student;

public class StudentMapper {
    
    public static Student toStudent(StudentCreateDto createDto){
        return new ModelMapper().map(createDto, Student.class);
    }
    public static Student updateToStudent(StudentUpdateDto updateDto){
        return new ModelMapper().map(updateDto, Student.class);
    }
    public static StudentResponseDto toDto(Student student){
        String role = student.getRole().name().substring("ROLE_".length());
        PropertyMap<Student, StudentResponseDto> props =  new PropertyMap<Student,StudentResponseDto>() {
            @Override
            protected void configure(){
                map().setRole(role);
            }
        };
        ModelMapper mapper =  new ModelMapper();
        mapper.addMappings(props);

        return mapper.map(student, StudentResponseDto.class);
    }
    public static List<StudentResponseDto> toListDto(List<Student> students){
        return students.stream().map(student -> toDto(student)).collect(Collectors.toList());
    }
     
}
