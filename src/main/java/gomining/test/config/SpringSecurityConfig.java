package gomining.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import gomining.test.jwt.JwtAuthorizationFilter;


@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebMvc
@Configuration
public class SpringSecurityConfig {
    
    private static final String[] DOCUMENTATION_OPENAPI = {
        "/docs/index.html",
        "/docs-park.html", "/docs-park/**",
        "/v3/api-docs/**",
        "/swagger-ui-custom.html", "/swagger-ui.html", "/swagger-ui/**",
        "/**.html", "/webjars/**", "/configuration/**", "/swagger-resources/**"
};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable()) 
            .httpBasic(basic -> basic.disable()) 
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeRequests(authorize -> authorize
                .antMatchers(HttpMethod.POST, "/api/v1/students").permitAll() 
                .antMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
                .antMatchers(DOCUMENTATION_OPENAPI).permitAll()
                .anyRequest().authenticated() 
            )
            .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);;
        
        return http.build();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter();
    }

    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}
