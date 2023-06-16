package com.turnos.turnos.repository;

import com.turnos.turnos.model.Instituto;
import com.turnos.turnos.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutoRepository extends JpaRepository<Instituto, Long>{
    
    List<Instituto> findByUser( User user );
    
}
