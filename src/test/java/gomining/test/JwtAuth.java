package gomining.test;

import java.util.function.Consumer;

import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import gomining.test.dto.StudentLoginDto;
import gomining.test.jwt.JwtToken;

public class JwtAuth {
    
    public static Consumer<HttpHeaders> getHeaderAuthorization(WebTestClient client, String email, String password){
        String token = client
                        .post()
                        .uri("/api/v1/auth")
                        .bodyValue(new StudentLoginDto(email, password))
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(JwtToken.class)
                        .returnResult().getResponseBody().getToken();
        return headers -> headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
