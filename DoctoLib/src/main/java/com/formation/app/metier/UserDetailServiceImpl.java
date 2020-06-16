package com.formation.app.metier;

import com.formation.app.dao.UserRepository;
import com.formation.app.entities.User;
import com.formation.app.entities.UserDetailsImpl;
import com.formation.app.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUserName(username);
            if (null == user) {
                throw new UsernameNotFoundException("No user named " + username);
            } else {
                return new UserDetailsImpl(user);
            }
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


}
