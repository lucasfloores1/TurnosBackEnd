package com.turnos.turnos.repository;

import com.turnos.turnos.model.Estudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudioRepository  extends JpaRepository< Estudio, Long >{
    
}
