package com.turnos.turnos.repository;

import com.turnos.turnos.model.Medico_Instituto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Medico_InstitutoRepository extends JpaRepository< Medico_Instituto, Long > {
    
}
