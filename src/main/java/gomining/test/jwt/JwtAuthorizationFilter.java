package gomining.test.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUserDatailsService detailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
         
        final String token =  request.getHeader(JwtUtils.JWT_AUTHORIZATION);

        if(token == null || !token.startsWith(JwtUtils.JWT_BEARER)){
            log.info("JWT is null or not contains Bearer");
            filterChain.doFilter(request, response);
            return;
        }
        if(!JwtUtils.isTokenValid(token)){
            log.warn("JWT is invalid or expirated");
            filterChain.doFilter(request, response);
            return;
        }
        String username = JwtUtils.getNameFromToken(token);

        toAuthentication(request, username);

        filterChain.doFilter(request, response);
    }
    private void toAuthentication(HttpServletRequest request, String name){
        UserDetails userDetails = detailsService.loadUserByUsername(name);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    


}
