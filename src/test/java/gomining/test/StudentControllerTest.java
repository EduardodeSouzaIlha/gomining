package gomining.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import gomining.test.dto.ActivityCreateDto;
import gomining.test.dto.StudentCreateDto;
import gomining.test.dto.StudentLoginDto;
import gomining.test.dto.StudentResponseDto;
import gomining.test.dto.mapper.StudentMapper;
import gomining.test.entity.Activity;
import gomining.test.repository.StudentRepository;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class StudentControllerTest {
    @Autowired
    WebTestClient testClient;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        // Insere alguns dados de teste no banco de dados
        StudentCreateDto student1 = new StudentCreateDto("Test", "123456", "04465412954", "51995084472", "teste@email.com" );
        studentRepository.save(StudentMapper.toStudent(student1));
    }



    @Test
    public void createStudent() {
        StudentResponseDto responseBody = testClient
                .post()
                .uri("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new StudentCreateDto("Pedro", "12345454878", "0454578687", "5198756423", "pedro@email.com" ))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(StudentResponseDto.class)
                .returnResult().getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody.getEmail()).isEqualTo("pedro@email.com");
        assertThat(responseBody.getRole()).isEqualTo("STUDENT");
    }
    @Test
    public void createActivity() {
        Activity responseBody = testClient
            .post()
            .uri("/api/v1/activities")
            .contentType(MediaType.APPLICATION_JSON)
            .headers(JwtAuth.getHeaderAuthorization(testClient, "pedro@email.com", "12345454878"))
            .bodyValue(new ActivityCreateDto("titulo", "descrição"))
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Activity.class)
            .returnResult()
            .getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody.getTitle()).isEqualTo("titulo");
        assertThat(responseBody.getDescription()).isEqualTo("descrição");
    }



  
    @AfterAll
    public void tearDown() {
        // Deleta os dados do banco 
        studentRepository.deleteAll();
    }
}

