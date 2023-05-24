package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.NuevoMedicoDTO;
import com.turnos.turnos.model.Horario;
import com.turnos.turnos.model.Instituto;
import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.Medico_Instituto;
import com.turnos.turnos.service.impl.HorarioServiceImpl;
import com.turnos.turnos.service.impl.InstitutoServiceImpl;
import com.turnos.turnos.service.impl.MedicoServiceImpl;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "http://localhost:4200/" )
public class MedicoController {
    
    @Autowired
    MedicoServiceImpl medicoService;
    InstitutoServiceImpl institutoService;
    HorarioServiceImpl horarioService;
    
    @GetMapping( "/medico/load" )
    @ResponseBody
    public List<Medico> getMedicos(){
        return medicoService.getMedicos();
    }
    
    @PostMapping( "/medico/create" )
    @ResponseBody
    public ResponseEntity<?> createMedico( NuevoMedicoDTO medicodto ){
        
        //Establezco el Instituto
        Instituto instituto = institutoService.getInstitutoById(medicodto.getIdInstituto());
        
        //Creo el medico
        Medico createdMedico = new Medico();
        createdMedico.setNombre(medicodto.getNombre());
        createdMedico.setDni(medicodto.getDni());
        createdMedico.setMail(medicodto.getMail());
        createdMedico.setDireccion(medicodto.getDireccion());
        createdMedico.setMatricula(medicodto.getMatricula());
        
        //Nueva Instancia de la relacion
        Medico_Instituto medico_Instituto = new Medico_Instituto();
        medico_Instituto.setMedico(createdMedico);
        medico_Instituto.setInstituto(instituto);
        
        //Creando Horarios
        HashSet<Horario> horariosCreados = new HashSet<>();
        for ( Horario horario : medicodto.getHorarios() ){
            horario.setMedico_instituto(medico_Instituto);
            horarioService.createHorario(horario);
            horariosCreados.add(horario);
        }
        
        //Asignando los horarios creados al medico_instituto
        medico_Instituto.setHorarios(horariosCreados);
        
        //Llamo al servicio para que cree los datos en la bd
        medicoService.createMedico(createdMedico, medico_Instituto);
        
        return ResponseEntity.ok(createdMedico);
    }
}
