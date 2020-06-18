package com.formation.app.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * class filter to authorization the authentications.
 * As we have implemented the filter responsible for authenticating users,
 * we now need to implement the filter responsible for user authorization
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        /*add in the header of the response after authorization */
        // all origin are authorized
        response.addHeader("Acces-Control-Allow-Origin", "*");
        //headers who are authorized from client
        response.addHeader("Access-Control-Allow-Headers",
              "Origin, Accept, X-Requested-With, Content-Type,"
                    +"Access-Control-Request-Method, "
                    +"Access-Control-Request-Headers,"
                    + "Authorization");
        // headers who are exposed allowing to client for reader them
         response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin," +
                "Access-Control-Allow-Credentials, Authorization");
         // Option request from clients allowing to consult server. if request is sent with OPTION, server sent response with parameters in the header
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK); //response 200
        }

       //get jeton from client
        String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING); // get token inside of header request after authentication
        System.out.println(jwtToken);
        //test if jeton is valid
        if(jwtToken==null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        //parse jeton and get body and username
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX,""))
                .getBody();
        String username = claims.getSubject();
        // get roles
        ArrayList<Map<String, String>>  roles = (ArrayList<Map<String, String>>)claims.get("roles");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach( r->{
            authorities.add(new SimpleGrantedAuthority(r.get("authority"))); // authority is the key of json object
        });
        // set authorisation
        UsernamePasswordAuthenticationToken authenticateUser =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticateUser);
        chain.doFilter(request, response);
        //super.doFilterInternal(request, response, chain);

    }
}

