package com.turnos.turnos.service;

import com.turnos.turnos.model.Paciente;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface IPacienteService {
    
    public List<Paciente> getPacientes();
    
    public ResponseEntity<Paciente> createPaciente(Paciente paciente);
    
    public void deletePaciente(Long id);
    
    public ResponseEntity<Paciente> updatePaciente( Long id, Paciente paciente );
    
}
