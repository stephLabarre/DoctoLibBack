package com.formation.app.dao;

import com.formation.app.entities.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;


public interface RdvRepository extends JpaRepository<Rdv, Integer> {
   @Query(value = "Select * from Rdv where DATE_FORMAT(start, '%Y-%M-%d') >= DATE_FORMAT(:start, '%Y-%M-%d')",
           nativeQuery = true)
   public List<Rdv> findByStart(Date start);
}
