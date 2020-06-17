package com.formation.app.services.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.app.entities.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * class of JWT Authentication. Implement methods attemptAuthentication(...) to filter authentications
 * and successfulAuthentication(...) to generate a token after authentication.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //User user = null;
        //if data send with wwww
        //String username = request.getParameter("username");
        // String password = request.getParameter("password");
        //get json object mapper to java object
        try {
            request.getInputStream();
         Utilisateur utilisateur =  new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);
            System.out.println("***********************");
            System.out.println("username "+utilisateur.getUsername());
            System.out.println("password "+utilisateur.getPassword());
            //return super.attemptAuthentication(request, response); //new ArrayList<>())
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(utilisateur.getUsername(),
                    utilisateur.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
       //generate token
        // warning : ne dont confuse our own User and org.springframework.security.core.userdetails.User
      /*  String token = JWT.create()
                .withSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()))
                ;
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
       */
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) authResult.getPrincipal();

        String jwt = Jwts.builder().setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact();
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
