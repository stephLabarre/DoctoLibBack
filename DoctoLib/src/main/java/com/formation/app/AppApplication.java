package com.formation.app;

import com.formation.app.entities.Role;
import com.formation.app.entities.Utilisateur;
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

        accountMetier.createUser(new Utilisateur("kapdjou", "narcisse", "3 boulevard", 95230,
                "soisy", "narcisse.kapdjou@akka.eu","0785555","02555555","nkapdjou", "1233"));
        accountMetier.createUser(new Utilisateur("Labarre", "stephane", "3 boulevard", 95230,
                "soisy", "narcisse.kapdjou@akka.eu","0785555","02555555","slabarre", "1233"));
        accountMetier.createUser(new Utilisateur("test", "test", "3 boulevard", 95230,
                "soisy", "narcisse.kapdjou@akka.eu","0785555","02555555","test", "1233"));
        accountMetier.addRole(new Role("ADMIN"));
        accountMetier.addRole(new Role("USER"));
        accountMetier.addRoleToUser("nkapdjou", "ADMIN");
        accountMetier.addRoleToUser("slabarre", "ADMIN");
        accountMetier.addRoleToUser("test", "USER");


    }
}
