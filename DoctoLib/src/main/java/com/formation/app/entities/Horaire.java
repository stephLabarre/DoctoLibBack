package com.formation.app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Horaire implements Serializable {

    @Id @GeneratedValue
    private int id;
    private String jour;
    private Date debutAMHour;
    private Date finAMHour;
    private Date debutPMHour;
    private Date finPMHour;

    public Horaire() {
    }

    public Horaire(String jour, Date debutAMHour, Date finAMHour, Date debutPMHour, Date finPMHour) {
        this.jour = jour;
        this.debutAMHour = debutAMHour;
        this.finAMHour = finAMHour;
        this.debutPMHour = debutPMHour;
        this.finPMHour = finPMHour;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public Date getDebutAMHour() {
        return debutAMHour;
    }

    public void setDebutAMHour(Date debutAMHour) {
        this.debutAMHour = debutAMHour;
    }

    public Date getFinAMHour() {
        return finAMHour;
    }

    public void setFinAMHour(Date finAMHour) {
        this.finAMHour = finAMHour;
    }

    public Date getDebutPMHour() {
        return debutPMHour;
    }

    public void setDebutPMHour(Date debutPMHour) {
        this.debutPMHour = debutPMHour;
    }

    public Date getFinPMHour() {
        return finPMHour;
    }

    public void setFinPMHour(Date finPMHour) {
        this.finPMHour = finPMHour;
    }
}