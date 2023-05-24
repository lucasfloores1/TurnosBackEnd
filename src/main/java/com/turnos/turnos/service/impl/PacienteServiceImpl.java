package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Paciente;
import com.turnos.turnos.repository.PacienteRepository;
import com.turnos.turnos.service.IPacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    public PacienteRepository pacienteRepository;
    
    @Override
    public List<Paciente> getPacientes() {
        
        return pacienteRepository.findAll();
        
    }

    @Override
    public ResponseEntity<Paciente> createPaciente(Paciente paciente) {
        
        Paciente createdPaciente = pacienteRepository.save(paciente);
        
        return ResponseEntity.ok(createdPaciente);
        
    }

    @Override
    public void deletePaciente(Long id) {
        
        pacienteRepository.deleteById(id);
        
    }

    @Override
    public ResponseEntity<Paciente> updatePaciente(Long id, Paciente toUpdatePaciente) {
        
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        
        paciente.setNombre ( toUpdatePaciente.getNombre() );
        paciente.setTel ( toUpdatePaciente.getTel() );
        paciente.setDireccion ( toUpdatePaciente.getDireccion() );
        paciente.setMail ( toUpdatePaciente.getMail() );
        
        Paciente updatedPaciente = pacienteRepository.save(paciente);
        
        return ResponseEntity.ok(updatedPaciente);
        
    }
    
}
