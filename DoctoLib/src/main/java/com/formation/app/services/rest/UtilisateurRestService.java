package com.formation.app.services.rest;

import com.formation.app.entities.Utilisateur;
import com.formation.app.metier.AccountMetier;
import com.formation.app.metier.UtilisateurMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurRestService {
    @Autowired
    AccountMetier accountMetier;

    @Autowired
    UtilisateurMetier utilisateurMetier;
    /*
        Request from rest client using /utiliseur with POST method for insert Object.
        RequestBody annotation who allow to map json to object.
        Exemple : {"nom":"kapdjou", "prenom":"narcisse", "email":"narcisse@akka.eu", "codePostal":95230, "login":"test","adresse":"3 boulevard",
        "codePostal":95230, "ville":"soisy", "email":"narcisse@akka.eu","tel":"072548855","numSecSociale":"0255444444"}
     */
    @RequestMapping(value="/saveUtilisateur", method=RequestMethod.POST)
    public Utilisateur creerUtilisateur(@RequestBody Utilisateur utilisateur){
        if(accountMetier.findUserByUsername(utilisateur.getUsername())!=null)
            new RuntimeException("user already exists");

         //utilisateur1 = new Utilisateur();
         //utilisateur1.setUsername(utilisateur.getUsername());
         //utilisateur1.setPassword(utilisateur.getPassword());
        accountMetier.createUser(utilisateur);
        // role by default
        accountMetier.addRoleToUser(utilisateur.getUsername(), "USER"); // role by default
        return utilisateur;
    }
    /*
        Request from rest client using /utilisateur/{id} for get object by id.
        @PathVariable annotation who allow to map variable json to java variable.
        Exemple : http://localhost:8080/utilisateur/1
     */
    @RequestMapping(value="/utilisateur/{id}", method = RequestMethod.GET)
    public Utilisateur consulterUtilisateur(@PathVariable int id){
        return utilisateurMetier.consulterUtilisateur(id);
    }

    @RequestMapping(value="/deleteUtilisateur/{id}", method=RequestMethod.DELETE)
    public void deleteUtilisateur(@PathVariable int id){
        utilisateurMetier.deleteUtilisateur(id);
    }

    @RequestMapping(value="/utilisateurs", method=RequestMethod.GET)
    public List<Utilisateur> consulterUtilisateurs(){
        return utilisateurMetier.consulterUtilisateurs();
    }

    /*
        Request from rest client using /updateUtilisateur with PUT method for udapte utilisateur Object.
        RequestBody annotation who allow to map json to object. Warning : the id of utilisateur must be defined into json request.
        Exemple : {"id":1, "nom":"kapdjou", "prenom":"narcisse", "email":"narcisse@akka.eu", "codePostal":95230, "login":"test","adresse":"3 boulevard",
        "codePostal":95230, "ville":"soisy", "email":"narcisse@akka.eu","tel":"072548855","numSecSociale":"0255444444"}
     */
    @RequestMapping(value="/updateUtilisateur", method=RequestMethod.PUT)
    public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur){
        return utilisateurMetier.updateUtilisateur(utilisateur);
    }

   /*
    use form with repassword
    @RequestMapping(value="/registerUser", method=RequestMethod.POST)
    public User registerUser(@RequestBody RegisterForm registerForm){
        if(!registerForm.getPassword().equals(registerForm.getRepassword()))
            new RuntimeException("make sure password and confirme password are the same");
        User user = accountMetier.findUserByUsername(registerForm.getUsername());
        if(user!=null)
            new RuntimeException("user already exists");

        user = new User();
        user.setUsername(registerForm.getUsername());
        user.setPassword(registerForm.getPassword());
        accountMetier.createUser(user);
        // role by default
        accountMetier.addRoleToUser(user.getUsername(), "USER"); // role by default
        return user;
    }  */

}
