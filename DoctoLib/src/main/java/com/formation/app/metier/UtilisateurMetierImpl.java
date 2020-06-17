package com.formation.app.metier;

import com.formation.app.dao.UtilisateurRepository;
import com.formation.app.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UtilisateurMetierImpl implements UtilisateurMetier {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur consulterUtilisateur(int id) {
        // null is the value to be returned if there is no value present
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if(utilisateur==null) throw new RuntimeException("utilisateur introuvable"); // exception non surveillée
        return utilisateur;
    }
    @Override
    public List<Utilisateur> consulterUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        // null is the value to be returned if there is no value present
        Utilisateur user = utilisateurRepository.findById(utilisateur.getId()).orElse(null);
        if(user==null) throw new RuntimeException("utilisateur introuvable"); // exception non surveillée
        try {
            user.setNom(utilisateur.getNom());
            user.setPrenom(utilisateur.getPrenom());
            user.setEmail(utilisateur.getEmail());
            user.setLogin(utilisateur.getUsername());
            user.setAdresse(utilisateur.getAdresse());
            user.setCodePostal(utilisateur.getCodePostal());
            user.setNumSecSociale(utilisateur.getNumSecSociale());
            user.setTel(utilisateur.getTel());
            utilisateurRepository.save(user);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUtilisateur(int id) {
        utilisateurRepository.deleteById(id);
    }

}
