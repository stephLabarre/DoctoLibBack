package com.formation.app.metier;

import com.formation.app.entities.Rdv;

import java.util.List;

public interface RdvMetier {

    public Rdv ajouterRdv(Rdv rdv);
    public void supprimerRdv(Rdv rdv);
    public List<Rdv> getRdvByJour(Long date);
}
