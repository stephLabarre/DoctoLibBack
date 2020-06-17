package com.formation.app;

import com.formation.app.entities.Role;
import com.formation.app.entities.User;
import com.formation.app.metier.AccountMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    @Autowired
    private AccountMetier accountMetier;

    public static void main(String[] args) {

        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

        accountMetier.createUser(new User("narcisse", "1233"));
        accountMetier.createUser(new User("stephane", "1233"));
        accountMetier.createUser(new User("test", "1233"));
        accountMetier.addRole(new Role("ADMIN"));
        accountMetier.addRole(new Role("USER"));
        accountMetier.addRoleToUser("narcisse", "ADMIN");
        accountMetier.addRoleToUser("stephane", "ADMIN");
        accountMetier.addRoleToUser("test", "USER");


    }
}
