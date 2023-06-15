package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.Turno;
import com.turnos.turnos.model.User;
import com.turnos.turnos.repository.TurnoRepository;
import com.turnos.turnos.repository.UserRepository;
import com.turnos.turnos.service.IMedicoService;
import com.turnos.turnos.service.ITurnoService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class TurnoServiceImpl implements ITurnoService {

    @Autowired
    public TurnoRepository turnoRepository;
    @Autowired
    public IMedicoService medicoService;
    @Autowired
    public UserRepository userRepository;
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger(EmailServiceImpl.class);
    
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
    @Transactional
    public void deleteTurno(Long id) {
        
        Turno turno = turnoRepository.findById(id).orElse(null);
        if(turno != null){
            logger.info("no dio nulo");   
            turnoRepository.delete(turno);
        }else {
            logger.info("dio nulo");
        }
    }

    @Override
    public ResponseEntity<Turno> updateTurno(Long id, Turno toUpdateTurno) {
        
        Turno turno = turnoRepository.findById(id).orElse(null);
        
        turno.setFecha ( toUpdateTurno.getFecha() );
        turno.setCargado ( toUpdateTurno.isCargado());
        turno.setConfirmado ( toUpdateTurno.isConfirmado() );
        turno.setCancelado(toUpdateTurno.isCancelado());
        
        Turno updatedTurno = turnoRepository.save(turno);
        
        return ResponseEntity.ok(updatedTurno);
        
    }
    
    @Override
    public Turno getTurnoById (Long id){
        return turnoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Turno> getTurnosByMedico(Long id) {
        
        Medico medico = medicoService.getMedicoById(id);
        return turnoRepository.findByMedico( medico );
    }

    @Override
    public List<Turno> getTurnosByUser(Long id) {
        
        User user = userRepository.findById(id).orElse(null);
        return turnoRepository.findByUser(user);
        
    }

    @Override
    public List<Turno> getTurnosForTomorrow() {
        return turnoRepository.getTurnosForTomorrow();
    }
}
