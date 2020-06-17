package com.formation.app.entities;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
public class Utilisateur implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private int codePostal;
    private String ville;
    private String email;
    private String tel;
    private String numSecSociale;
    @Column(unique = true)
    String username;
    String password;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.
            REMOVE }, fetch = FetchType.EAGER)
    List<Role> roles = new ArrayList<Role>();

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String adresse, int codePostal,
                       String ville, String email, String tel, String numSecSociale,
                       String username, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.username = username;
        this.ville = ville;
        this.email = email;
        this.tel = tel;
        this.numSecSociale = numSecSociale;
        this.password = password;
    }

    public Utilisateur(String nom, String prenom, String adresse, int codePostal,
                       String ville, String email, String tel, String numSecSociale,
                       String username, String password, List<Role> roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.username = username;
        this.ville = ville;
        this.email = email;
        this.tel = tel;
        this.numSecSociale = numSecSociale;
        this.password = password;
        this.roles = roles;
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

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername( String username){ return this.username = username; }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String mdp) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
