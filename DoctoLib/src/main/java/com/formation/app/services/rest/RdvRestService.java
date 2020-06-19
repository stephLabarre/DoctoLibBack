package com.formation.app.services.rest;

import com.formation.app.entities.Rdv;
import com.formation.app.metier.RdvMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RdvRestService {
    @Autowired
    private RdvMetier rdvMetier;

    @RequestMapping(value="/ajouterRdv", method= RequestMethod.POST)
    public Rdv creerRdv(@RequestBody Rdv rdv){
        return rdvMetier.ajouterRdv(rdv);
    }

    @RequestMapping(value ="/supprimerRdv", method = RequestMethod.DELETE)
    public void supprimerRdv(@RequestBody Rdv rdv){
        rdvMetier.supprimerRdv(rdv);
    }

    @RequestMapping(value ="/rdvs", method = RequestMethod.GET)
    public List<Rdv> listeRdv(@RequestParam Long date){
        System.out.println("DATE:" + date);
        List<Rdv> rdvs = rdvMetier.getRdvByJour(date);
        return rdvs;
    }
}