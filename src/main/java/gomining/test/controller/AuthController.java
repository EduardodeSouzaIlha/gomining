package gomining.test.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gomining.test.dto.StudentLoginDto;
import gomining.test.exception.ErrorMessage;
import gomining.test.jwt.JwtToken;
import gomining.test.jwt.JwtUserDatailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AuthController {
    
    private final JwtUserDatailsService datailsService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> authentication(@RequestBody StudentLoginDto studentLoginDto, HttpServletRequest request){
        System.out.println(studentLoginDto.getName());
        log.info("Auth", studentLoginDto.getName());
        try{
            UsernamePasswordAuthenticationToken  authenticationToken = new UsernamePasswordAuthenticationToken(studentLoginDto.getName(), studentLoginDto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = datailsService.getTokenAuthenticated(studentLoginDto.getName());
            return ResponseEntity.ok(token);
        }catch(AuthenticationException e){
            log.warn("Bad Credentials from username '{}'", studentLoginDto.getName());

        }
        return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Bad Credentials"));
    }
}
