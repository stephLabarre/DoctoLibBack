package com.formation.app.services.rest;

import com.formation.app.entities.Horaire;
import com.formation.app.metier.HoraireMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HoraireRestService {
    @Autowired
    HoraireMetier horaireMetier;

    /*
        Request from rest client using /horaire with POST method for insert Object.
        RequestBody annotation who allow to map json to object.
    */
    @RequestMapping(value="/horaire", method=RequestMethod.POST)
    public Horaire creerHoraire(@RequestBody Horaire horaire){
        return horaireMetier.creerHoraire(horaire);
    }
    /*
        Request form rest client using /horaire/{id} for get object by id.
        @PathVariable annotation who allow to map variable json to java variable.
        Exemple : http://localhost:8080/horaire/1
     */
    @RequestMapping(value="/horaire/{id}", method = RequestMethod.GET)
    public Horaire consulterHoraire(@PathVariable int id){
        return horaireMetier.consulterHoraire(id);
    }

    @RequestMapping(value="/deleteHoraire/{id}", method=RequestMethod.DELETE)
    public void deleteHoraire(@PathVariable int id){
        horaireMetier.deleteHoraire(id);
    }

    @RequestMapping(value="/horaires", method=RequestMethod.GET)
    public List<Horaire> consulterHoraires(){
        List<Horaire>  liste = horaireMetier.consulterHoraires();
        liste.forEach(e ->
                    System.out.println("Jour = " + e.getJour() + "\n"
                            + " Date Debut AM = " + e.getDebutAMHour() + "\n"
                            + " Date Fin AM = " + e.getFinAMHour() + "\n"
                            + " Date Debut PM = " + e.getDebutPMHour() + "\n"
                            + " Date Fin PM + " + e.getFinPMHour())
                );
        return liste;
    }

    /*
        Request from rest client using /updateHoraire with PUT method for udapte Horaire Object.
        RequestBody annotation who allow to map json to object. Warning : the id of Horaire must be defined into json request.
     */
    @RequestMapping(value="/updateHoraire", method=RequestMethod.PUT)
    public Horaire updateHoraire(@RequestBody Horaire horaire){
        return horaireMetier.updateHoraire(horaire);
    }
}