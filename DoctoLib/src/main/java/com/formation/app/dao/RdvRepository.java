package com.formation.app.dao;

import com.formation.app.entities.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface RdvRepository extends JpaRepository<Rdv, Integer> {
   @Query("Select r from Rdv r where r.jour = :start")
   public List<Rdv> findByStart(Date start);
}
