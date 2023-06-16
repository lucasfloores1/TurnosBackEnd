package com.turnos.turnos.repository;

import com.turnos.turnos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository< User , Long >{

    User findByUsername( String username );
}
