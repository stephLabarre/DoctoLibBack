package com.formation.app.metier;

import com.formation.app.entities.Utilisateur;

import java.util.List;

public interface UtilisateurMetier {

    public Utilisateur creerUtilisateur(Utilisateur utilisateur);
    public Utilisateur consulterUtilisateur(int id);
    public Utilisateur searchUtilisateurByLoginAndMdp(String login, String mdp);
    public List<Utilisateur> consulterUtilisateurs();
    public Utilisateur updateUtilisateur(Utilisateur utilisateur);
    public void deleteUtilisateur(int id);
}
