package com.formation.app.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; /// ????

    /* bean of spring boot instantiation */
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //service use to load user by name from database
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder); //???
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // desactivate csrf

        /*Simple auhentication based on password */
        http.formLogin(); //for use formula by default of Spring
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**", "/saveUtilisateur/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/utilisateurs/**").hasAnyAuthority("ADMIN");
       // http.cors().and().authorizeRequests().anyRequest().fullyAuthenticated();
        http.authorizeRequests().anyRequest().authenticated();
       // http.httpBasic();

        /*we use two filters for authentication based on token */
        http.addFilter(new JWTAuthenticationFilter(authenticationManager())); // with jwt
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); // with jwt before JWTAuthenticationFilter
    }


}
