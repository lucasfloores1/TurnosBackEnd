package com.turnos.turnos.repository;

import com.turnos.turnos.model.Horario;
import com.turnos.turnos.model.Medico_Instituto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository< Horario, Long >{
    
    List<Horario> findByMedicoInstituto( Medico_Instituto MedicoInstituto );
    
}
