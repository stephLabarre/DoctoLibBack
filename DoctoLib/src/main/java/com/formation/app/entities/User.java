package com.formation.app.entities;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long num;
    @Column(unique = true)
    String username;
    String password;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.
            REMOVE }, fetch = FetchType.EAGER)
    List<Role> roles = new ArrayList<Role>();


    public User(){
    }
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //@JsonIgnore //: security : ignore the user password of json format when register user.
    public String getPassword() {
        return password;
    }
    //@JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
