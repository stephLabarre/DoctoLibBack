package com.formation.app.dao;

import com.formation.app.entities.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;


public interface RdvRepository extends JpaRepository<Rdv, Integer> {
   public List<Rdv> findByStart(Date start);
}
