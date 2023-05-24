package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Turno;
import com.turnos.turnos.repository.TurnoRepository;
import com.turnos.turnos.service.ITurnoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class TurnoServiceImpl implements ITurnoService {

    @Autowired
    public TurnoRepository turnoRepository;
    
    @Override
    public List<Turno> getTurnos() {
        
        return turnoRepository.findAll();
        
    }

    @Override
    public ResponseEntity<Turno> createTurno(Turno turno) {
        
        Turno createdTurno = turnoRepository.save(turno);
        
        return ResponseEntity.ok(createdTurno);
        
    }

    @Override
    public void deleteTurno(Long id) {
        
        turnoRepository.deleteById(id);
        
    }

    @Override
    public ResponseEntity<Turno> updateTurno(Long id, Turno toUpdateTurno) {
        
        Turno turno = turnoRepository.findById(id).orElse(null);
        
        turno.setFecha ( toUpdateTurno.getFecha() );
        turno.setCargado ( toUpdateTurno.isCargado());
        turno.setConfirmado ( toUpdateTurno.isConfirmado() );
        
        Turno updatedTurno = turnoRepository.save(turno);
        
        return ResponseEntity.ok(updatedTurno);
        
    }
    
}
