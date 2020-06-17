package com.formation.app.metier;

import com.formation.app.dao.RoleRepository;
import com.formation.app.dao.UtilisateurRepository;
import com.formation.app.entities.Role;
import com.formation.app.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountMetierImpl implements AccountMetier {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Utilisateur createUser(Utilisateur utilisateur) {
        String hashPW = bCryptPasswordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(hashPW);
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Role role = roleRepository.findRoleByRoleName(roleName);
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        utilisateur.getRoles().add(role);
        //userRepository.save(user); // les methodes sont transactionnelle. quand le role est ajouter automatique la base se met à jour
    }

    @Override
    public Utilisateur findUserByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String role) {
        return roleRepository.findRoleByRoleName(role);
    }

}
