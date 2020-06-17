package com.formation.app.metier;

import com.formation.app.entities.Role;
import com.formation.app.entities.Utilisateur;

public interface AccountMetier {

    public Utilisateur createUser(Utilisateur utilisateur);

    public Role addRole(Role role);

    public void addRoleToUser(String username, String roleName);

    public Utilisateur findUserByUsername(String username);

    public Role findRoleByRoleName(String role);

}