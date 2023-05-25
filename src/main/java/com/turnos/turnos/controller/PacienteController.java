
package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.NuevoPacienteDTO;
import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Paciente;
import com.turnos.turnos.model.Paciente_ObraSocial;
import com.turnos.turnos.model.Plan;
import com.turnos.turnos.service.impl.ObraSocialServiceImpl;
import com.turnos.turnos.service.impl.PacienteServiceImpl;
import com.turnos.turnos.service.impl.PlanServiceImpl;
import java.util.Collections;
import java.util.HashSet;
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
    @Autowired
    private ObraSocialServiceImpl obraSocialService;
    @Autowired
    private PlanServiceImpl planService;
    
    @GetMapping( "/paciente/load" )
    @ResponseBody
    public List<Paciente> loadPaciente(){
        return pacienteService.getPacientes();
    }
    
    @PostMapping( "/paciente/create" )
    public ResponseEntity<?> createPaciente( @RequestBody NuevoPacienteDTO pacientedto ) {
        
        //Establezco la obra social
        ObraSocial obraSocial = obraSocialService.getObraSocialById(pacientedto.getIdObraSocial());
        
        //Establezco el plan
        Plan plan = planService.getPlanById(pacientedto.getIdPlan());
        
        //Creo el paciente
        Paciente createdPaciente = new Paciente();
        
        createdPaciente.setNombre(pacientedto.getNombre());
        createdPaciente.setDni(pacientedto.getDni());
        createdPaciente.setMail(pacientedto.getMail());
        createdPaciente.setDireccion(pacientedto.getDireccion());
        
        //Nueva Instancia de la relacion
        Paciente_ObraSocial paciente_ObraSocial = new Paciente_ObraSocial();
        paciente_ObraSocial.setPaciente(createdPaciente);
        paciente_ObraSocial.setObraSocial(obraSocial);
        paciente_ObraSocial.setPlan(plan);
        paciente_ObraSocial.setAfiliado(pacientedto.getAfiliado());
        
        //Relaciono paciente con la obra social
        createdPaciente.setObrasSociales( new HashSet<>( Collections.singletonList( paciente_ObraSocial ) ) );
        
        //Llama al servicio que guarda al paciente en la base de datos
        pacienteService.createPaciente(createdPaciente, paciente_ObraSocial);

        return ResponseEntity.ok( createdPaciente );
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
