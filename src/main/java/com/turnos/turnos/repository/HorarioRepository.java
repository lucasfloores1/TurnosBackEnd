package com.turnos.turnos.repository;

import com.turnos.turnos.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository< Horario, Long >{
    
}
