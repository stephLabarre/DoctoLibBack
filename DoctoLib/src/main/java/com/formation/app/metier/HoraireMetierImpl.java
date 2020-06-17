package com.formation.app.metier;

import com.formation.app.dao.HoraireRepository;
import com.formation.app.entities.Horaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HoraireMetierImpl implements HoraireMetier {
    @Autowired
    private HoraireRepository horaireRepository;

    @Override
    public Horaire creerHoraire(Horaire horaire) {
        Horaire h = horaireRepository.findAll().stream().filter(e -> e.getJour().equals(horaire.getJour())).findFirst().orElse(null);
        if(h == null) {
            return horaireRepository.save(horaire);
        } else {
            return updateHoraire(horaire);
        }
    }

    @Override
    public Horaire consulterHoraire(int id) {
        // null is the value to be returned if there is no value present
        Horaire horaire = horaireRepository.findById(id).orElse(null);
        if(horaire==null) throw new RuntimeException("horaire introuvable"); // exception non surveillée
        return horaire;
    }
    @Override
    public List<Horaire> consulterHoraires() {
        return horaireRepository.findAll();
    }

    @Override
    public Horaire updateHoraire(Horaire horaire) {
        // null is the value to be returned if there is no value present
        Horaire h = horaireRepository.findAll().stream().filter(e -> e.getJour().equals(horaire.getJour())).findFirst().orElse(null);
        if(horaire==null) throw new RuntimeException("horaire introuvable"); // exception non surveillée
        try {
            h.setJour (horaire.getJour());
            h.setDebutAMHour(horaire.getDebutAMHour());
            h.setFinAMHour(horaire.getFinAMHour());
            h.setDebutPMHour(horaire.getDebutPMHour());
            h.setFinPMHour(horaire.getFinPMHour());
            horaireRepository.save(h);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return h;
    }

    @Override
    public void deleteHoraire(int id) {
        horaireRepository.deleteById(id);
    }

}
