package com.formation.app.metier;

import com.formation.app.dao.RdvRepository;
import com.formation.app.entities.Rdv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RdvMetierImpl implements RdvMetier {
    @Autowired
    private RdvRepository rdvRepository;

    @Override
    public Rdv ajouterRdv(Rdv rdv) {
        return rdvRepository.save(rdv);
    }

    @Override
    public void supprimerRdv(Rdv rdv) {
        rdvRepository.delete(rdv);
    }

    @Override
    public List<Rdv> getRdvByJour(Long date) {
        Date d = new Date(date);
        List<Rdv> liste = rdvRepository.findByStart(d);
        return liste;
    }
}
