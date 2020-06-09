package com.formation.app.metier;

import com.formation.app.dao.UtilisateurRepository;
import com.formation.app.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurMetierImpl implements UtilisateurMetier {
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Override
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur consulterUtilisateur(int id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if(utilisateur==null) throw new RuntimeException("utilisateur introuvable"); // exception non surveillée
        return utilisateur;
    }
}
