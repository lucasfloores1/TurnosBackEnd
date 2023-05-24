package com.turnos.turnos.repository;

import com.turnos.turnos.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository< Plan, Long > {
    
}
