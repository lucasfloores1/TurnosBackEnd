package com.turnos.turnos.service;

import com.turnos.turnos.model.Paciente;
import com.turnos.turnos.model.Paciente_ObraSocial;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface IPacienteService {
    
    public List<Paciente> getPacientes();
    
    public Paciente getPacienteById(Long id);
    
    public void createPaciente(Paciente paciente, Paciente_ObraSocial paciente_obraSocial);
    
    public void deletePaciente(Long id);
    
    public ResponseEntity<Paciente> updatePaciente( Long id, Paciente paciente );
    
}
