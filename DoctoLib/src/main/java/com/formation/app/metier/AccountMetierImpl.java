package com.formation.app.metier;

import com.formation.app.dao.RoleRepository;
import com.formation.app.dao.UserRepository;
import com.formation.app.entities.Role;
import com.formation.app.entities.User;
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
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user) {
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        return userRepository.save(user);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Role role = roleRepository.findRoleByRoleName(roleName);
        User user = userRepository.findByUsername(username);
        user.getRoles().add(role);
        //userRepository.save(user); // les methodes sont transactionnelle. quand le role est ajouter automatique la base se met à jour
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String role) {
        return roleRepository.findRoleByRoleName(role);
    }

}
