package com.formation.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Utilisateur implements Serializable {
    @Id @GeneratedValue
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private int codePostale;
    private String ville;
    private String email;
    private String tel;
    private String numSecSociale;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String adresse, int codePostale,
                       String ville, String email, String tel, String numSecSociale) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.ville = ville;
        this.email = email;
        this.tel = tel;
        this.numSecSociale = numSecSociale;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(int codePostale) {
        this.codePostale = codePostale;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNumSecSociale() {
        return numSecSociale;
    }

    public void setNumSecSociale(String numSecSociale) {
        this.numSecSociale = numSecSociale;
    }
}
