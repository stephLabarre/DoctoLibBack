package com.formation.app.metier;

import com.formation.app.entities.Utilisateur;

public interface UtilisateurMetier {

    public Utilisateur creerUtilisateur(Utilisateur utilisateur);
    public Utilisateur consulterUtilisateur(int id);
}
