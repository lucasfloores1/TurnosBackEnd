package com.turnos.turnos.repository;

import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Plan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository< Plan, Long > {
 
    public List<Plan> findByObraSocial( ObraSocial obraSocial );
    
}
