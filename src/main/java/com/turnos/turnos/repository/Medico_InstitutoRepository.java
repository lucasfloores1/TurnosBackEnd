package com.turnos.turnos.repository;

import com.turnos.turnos.model.Instituto;
import com.turnos.turnos.model.Medico_Instituto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Medico_InstitutoRepository extends JpaRepository< Medico_Instituto, Long > {
    
    @Query("SELECT mi FROM Medico_Instituto mi WHERE mi.medico.id = :medicoId AND mi.instituto.id = :institutoId")
    Medico_Instituto findByMedicoIdAndInstitutoId(@Param("medicoId") Long medicoId, @Param("institutoId") Long institutoId);
    
    List<Medico_Instituto> findAllByInstituto ( Instituto instituto );
    
}
