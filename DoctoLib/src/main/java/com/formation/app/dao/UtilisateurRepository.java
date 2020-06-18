package com.formation.app.dao;

import com.formation.app.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    public Utilisateur findByLoginAndMdp(String login, String Mdp);

}
