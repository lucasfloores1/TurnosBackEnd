package com.turnos.turnos.repository;

import com.turnos.turnos.model.Instituto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutoRepository extends JpaRepository<Instituto, Long>{
    
}
