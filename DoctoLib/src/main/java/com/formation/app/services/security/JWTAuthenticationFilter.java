package com.formation.app.services.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.app.entities.User;
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
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

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
         User user =  new ObjectMapper().readValue(request.getInputStream(), User.class);
            System.out.println("***********************");
            System.out.println("username"+user.getUsername());
            System.out.println("password"+user.getPassword());
            //return super.attemptAuthentication(request, response); //new ArrayList<>())
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
      // User springUser = (User) authResult.getPrincipal();
       //generate token
        // warning : ne dont confuse our own User and org.springframework.security.core.userdetails.User
        String token = JWT.create()
                .withSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        /*
        String jwt = Jwts.builder().setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .claim("roles", springUser.getRoles()) //springUser.getAuthorities()
                .compact();
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt); */
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
