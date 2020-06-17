package com.formation.app.metier;

import com.formation.app.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AccountMetier accountMetier;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Utilisateur utilisateur = accountMetier.findUserByUsername(username);
            if (null == utilisateur) //{
                throw new UsernameNotFoundException("No user named " + username);
            //} else {
                //return new UserDetailsImpl(user);
                 //return new User(user.getUsername(), user.getPassword(), emptyList());
           // }
          // if we dont use UserDetailsImpl
            Collection<GrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(r->{
                authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
            });
            return new org.springframework.security.core.userdetails.User(utilisateur.getUsername(),
                    utilisateur.getPassword(), authorities);
            //return new User(user.getUsername(), user.getPassword(), authorities);
    }


}
