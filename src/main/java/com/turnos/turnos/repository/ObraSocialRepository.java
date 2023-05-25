package com.turnos.turnos.repository;

import com.turnos.turnos.model.ObraSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {
    
}
