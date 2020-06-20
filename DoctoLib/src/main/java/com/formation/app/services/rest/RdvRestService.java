package com.formation.app.services.rest;

import com.formation.app.entities.Rdv;
import com.formation.app.metier.RdvMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


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
        System.out.println("ResService -> date recu en timestamp :" + date);
        Date dateToSend = new Date(date);
        LocalDate localDate = dateToSend.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        System.out.println("DAY "+day);
        System.out.println("Month "+month);
        System.out.println("ResService -> date envoyée en date :" + dateToSend);
        List<Rdv> rdvs = rdvMetier.getRdvByJour(day, month);
        System.out.println("liste des rendezvous du jour:");
        rdvs.forEach(rdv -> System.out.println(rdv.getStart()));
        return rdvs;
    }
}