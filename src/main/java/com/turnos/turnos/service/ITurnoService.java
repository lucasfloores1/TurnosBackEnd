package com.turnos.turnos.service;

import com.turnos.turnos.model.Turno;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ITurnoService {
    
    public List<Turno> getTurnos();
    
    public ResponseEntity<Turno> createTurno(Turno turno);
    
    public void deleteTurno(Long id);
    
    public ResponseEntity<Turno> updateTurno( Long id, Turno turno );
    
    public Turno getTurnoById (Long id);   
    
}
