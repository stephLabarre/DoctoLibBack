package com.formation.app.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
/*
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

 */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING); // get token inside of header request after authentication
        if(jwtToken==null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX,""))
                .getBody();
        String username = claims.getSubject();
        ArrayList<Map<String, String>>  roles = (ArrayList<Map<String, String>>)claims.get("roles");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach( r->{
            authorities.add(new SimpleGrantedAuthority(r.get("authority"))); // authority is the key of json object
        });
        UsernamePasswordAuthenticationToken authenticateUser =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticateUser);
        chain.doFilter(request, response);
        //super.doFilterInternal(request, response, chain);

    }
}

