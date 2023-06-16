package com.turnos.turnos.repository;

import com.turnos.turnos.model.Paciente;
import com.turnos.turnos.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
    List<Paciente> findByUser( User user );
    
}
