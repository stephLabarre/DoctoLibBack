package com.formation.app.dao;

import com.formation.app.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("select u from Utilisateur u "+" where u.login = ?1")
    Optional<Utilisateur> findUtilisateurWithLogin(String login);

}
