package com.formation.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Rdv implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private Date start;

    public Rdv() {
    }

    public Rdv(int id, String title, Date start) {
        this.id = id;
        this.title = title;
        this.start = start;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
