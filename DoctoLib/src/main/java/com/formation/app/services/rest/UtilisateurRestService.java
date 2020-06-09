package com.formation.app.services.rest;

import com.formation.app.entities.Utilisateur;
import com.formation.app.metier.UtilisateurMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UtilisateurRestService {
    @Autowired
    UtilisateurMetier utilisateurMetier;

    /*
        Request from rest client using /utiliseur with POST method for insert Object.
        RequestBody annotation who allow to map json to object.
        Exemple : {"nom":"kapdjou", "prenom":"narcisse", "adresse":"narcisse@akka.eu",
        "codePostale":95230, "ville":"soisy", "email":"narcisse@akka.eu","tel":"072548855","numSecSociale":"0255444444"}
     */
    @RequestMapping(value="/utilisateur", method=RequestMethod.POST)
    public Utilisateur creerUtilisateur(@RequestBody Utilisateur utilisateur){
        return utilisateurMetier.creerUtilisateur(utilisateur);
    }
    /*
        Request form rest client using /utilisateur/{id} for get object by id.
        @PathVariable annotation who allow to map variable json to java variable.
        Exemple : http://localhost:8080/utilisateur/1
     */
    @RequestMapping(value="/utilisateur/{id}", method = RequestMethod.GET)
    public Utilisateur consulterUtilisateur(@PathVariable int id){
        return utilisateurMetier.consulterUtilisateur(id);
    }


}
