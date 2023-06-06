package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Paciente;
import com.turnos.turnos.model.Paciente_ObraSocial;
import com.turnos.turnos.model.User;
import com.turnos.turnos.repository.PacienteRepository;
import com.turnos.turnos.repository.Paciente_ObraSocialRepository;
import com.turnos.turnos.repository.UserRepository;
import com.turnos.turnos.service.IPacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    public PacienteRepository pacienteRepository;
    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    public Paciente_ObraSocialRepository paciente_obraSocialRepository;
    
    @Override
    public List<Paciente> getPacientes() {
        
        return pacienteRepository.findAll();
        
    }

    @Override
    public void createPaciente(Paciente paciente, Paciente_ObraSocial paciente_obraSocial) {
        
        pacienteRepository.save( paciente );
        paciente_obraSocialRepository.save( paciente_obraSocial );
        
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

    @Override
    public Paciente getPacienteById(Long id) {
        
        return pacienteRepository.findById(id).orElse(null);
        
    }

    @Override
    public List<Paciente> getPacientesByUser(Long id) {
        
        User user = userRepository.findById(id).orElse(null);
        return pacienteRepository.findByUser(user);
        
    }
    
}
