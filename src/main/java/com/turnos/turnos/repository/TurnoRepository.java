
package com.turnos.turnos.repository;

import com.turnos.turnos.model.Estudio;
import com.turnos.turnos.model.Instituto;
import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Paciente;
import com.turnos.turnos.model.Plan;
import com.turnos.turnos.model.Turno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>{
    
    List<Turno> findByPaciente ( Paciente paciente );
    
    List<Turno> findByObraSocial ( ObraSocial obraSocial );
    
    List<Turno> findByPlan ( Plan plan );
    
    List<Turno> findByMedico ( Medico medico );
    
    List<Turno> findByInstituto ( Instituto instituto );
    
    List<Turno> findByEstudio ( Estudio estudio );
    
}
