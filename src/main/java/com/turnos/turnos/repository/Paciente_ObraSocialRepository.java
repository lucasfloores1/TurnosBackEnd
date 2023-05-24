package com.turnos.turnos.repository;

import com.turnos.turnos.model.Paciente_ObraSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Paciente_ObraSocialRepository extends JpaRepository< Paciente_ObraSocial, Long > {
    
}
