package com.formation.app.dao;

import com.formation.app.entities.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface RdvRepository extends JpaRepository<Rdv, Integer> {
   @Query("select r from Rdv r where r.start <= :datejour")
   public List<Rdv> findByStartBeforeDay(@Param("datejour")Date start);

   @Query("select r from Rdv r where r.start >= :datejour")
   public List<Rdv> findByStartAfterDay(@Param("datejour")Date start);

   @Query("SELECT r FROM Rdv r WHERE EXTRACT (day FROM r.start) = :day AND EXTRACT (month FROM r.start) = :month")
   public List<Rdv> findByStart(@Param("day") int day, @Param("month") int month);
}