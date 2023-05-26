package com.turnos.turnos.repository;

import com.turnos.turnos.model.Paciente;
import com.turnos.turnos.model.Paciente_ObraSocial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Paciente_ObraSocialRepository extends JpaRepository< Paciente_ObraSocial, Long > {
    
    List<Paciente_ObraSocial> findByPaciente( Paciente paciente );
    
    void deleteByPacienteId(Long pacienteId);
    
}
