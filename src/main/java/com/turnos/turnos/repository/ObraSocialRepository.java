package com.turnos.turnos.repository;

import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {
    
    List<ObraSocial> findByUser( User user );
    
    ObraSocial findByNombreAndUser( String nombre, User user );
}
