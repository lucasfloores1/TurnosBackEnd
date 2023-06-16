package com.turnos.turnos.repository;

import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository< Medico, Long > {
    
    List<Medico> findByUser( User user );
    
}
