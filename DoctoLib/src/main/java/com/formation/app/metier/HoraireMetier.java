package com.formation.app.metier;

import com.formation.app.entities.Horaire;

import java.util.List;

public interface HoraireMetier {

    public Horaire creerHoraire(Horaire horaire);
    public Horaire consulterHoraire(int id);
    public List<Horaire> consulterHoraires();
    public Horaire updateHoraire(Horaire horaire);
    public void deleteHoraire(int id);
}