
package com.turnos.turnos.controller;

import com.turnos.turnos.model.Paciente;
import com.turnos.turnos.service.impl.PacienteServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "http://localhost:4200/" )
public class PacienteController {
    
    @Autowired
    private PacienteServiceImpl pacienteService;
    
    @GetMapping( "/paciente/load" )
    @ResponseBody
    public List<Paciente> loadPaciente(){
        return pacienteService.getPacientes();
    }
    
    @PostMapping( "/paciente/create" )
    public ResponseEntity<Paciente> createPaciente( @RequestBody Paciente paciente ) {
        ResponseEntity<Paciente> response;
        
        Paciente createdPaciente = pacienteService.createPaciente(paciente).getBody();
        
        if ( createdPaciente != null ){
            
        response = ResponseEntity.ok(createdPaciente);
            
        }else {
            
            response = ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
            
        }
        return response;
    }  
    
    @DeleteMapping( "/paciente/delete/{id}" )
    public void deletePaciente( @PathVariable Long id ){
        pacienteService.deletePaciente(id);
    }
    
    @PutMapping( "/paciente/update/{id}" )
    public ResponseEntity<Paciente> updatePaciente( @PathVariable Long id, @RequestBody Paciente toUpdatePaciente ){
        
        return pacienteService.updatePaciente(id, toUpdatePaciente);
    
    };
        
    
}
