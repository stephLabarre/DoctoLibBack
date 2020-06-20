package com.formation.app.metier;

import com.formation.app.entities.Rdv;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface RdvMetier {

    public Rdv ajouterRdv(Rdv rdv);
    public void supprimerRdv(Rdv rdv);
    public List<Rdv> getRdvByBeforeDay(Date date);
    public List<Rdv> getRdvByAfterDay(Date date);
    public List<Rdv> getRdvByJour(int day, int month);

}
