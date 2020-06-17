package com.formation.app.metier;

import com.formation.app.entities.Role;
import com.formation.app.entities.User;

public interface AccountMetier {

    public User createUser(User user);

    public Role addRole(Role role);

    public void addRoleToUser(String username, String roleName);

    public User findUserByUsername(String username);

    public Role findRoleByRoleName(String role);

}