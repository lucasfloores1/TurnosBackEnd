package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.GetMedicoDTO;
import com.turnos.turnos.DTO.HorarioDTO;
import com.turnos.turnos.DTO.InstitutoDTO;
import com.turnos.turnos.DTO.NuevoMedicoDTO;
import com.turnos.turnos.model.Horario;
import com.turnos.turnos.model.Instituto;
import com.turnos.turnos.model.Medico;
import com.turnos.turnos.model.Medico_Instituto;
import com.turnos.turnos.model.User;
import com.turnos.turnos.service.impl.HorarioServiceImpl;
import com.turnos.turnos.service.impl.InstitutoServiceImpl;
import com.turnos.turnos.service.impl.MedicoServiceImpl;
import com.turnos.turnos.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "http://localhost:4200/" )
public class MedicoController {
    
    @Autowired
    MedicoServiceImpl medicoService;
    @Autowired
    InstitutoServiceImpl institutoService;
    @Autowired
    HorarioServiceImpl horarioService;
    @Autowired
    UserServiceImpl userService;
    
    @GetMapping( "/medico/load" )
    @ResponseBody
    public List<Medico> getMedicos(){
        return medicoService.getMedicos();
    }
    
    @GetMapping( "/medico/user/{id}" )
    @ResponseBody
    public List<Medico> getMedicosByUser( @PathVariable Long id ) {
        
        return medicoService.getMedicosByUser(id);
        
    }
    
    @PostMapping( "/medico/create" )
    @ResponseBody
    public ResponseEntity<?> createMedico(@RequestBody NuevoMedicoDTO medicodto ){
        
        //Establezco el Instituto
        Instituto instituto = institutoService.getInstitutoById(medicodto.getIdInstituto());
        
        //Creo el medico
        Medico createdMedico = new Medico();
        createdMedico.setUser(userService.getUserById(medicodto.getUserId()));
        createdMedico.setNombre(medicodto.getNombre());
        createdMedico.setTel(medicodto.getTel());
        createdMedico.setDni(medicodto.getDni());
        createdMedico.setMail(medicodto.getMail());
        createdMedico.setDireccion(medicodto.getDireccion());
        createdMedico.setMatricula(medicodto.getMatricula());
        
        //Guardando el medico
        Medico savedMedico = medicoService.addMedico(createdMedico);
        
        //Nueva Instancia de la relacion
        Medico_Instituto createdMedico_Instituto = new Medico_Instituto();
        createdMedico_Instituto.setMedico(savedMedico);
        createdMedico_Instituto.setInstituto(instituto);
        
        //Guardar la instancia
        Medico_Instituto savedMedico_Instituto = medicoService.addMedico_Instituto(createdMedico_Instituto);
        
        // Establecer la relación en el objeto Medico
        Set<Medico_Instituto> medicoInstitutoSet = new HashSet<>();
        medicoInstitutoSet.add(savedMedico_Instituto);
        savedMedico.setMedicoInstituto(medicoInstitutoSet);
        
        //Creando Horarios
        Set<Horario> horariosCreados = new HashSet<>();
        for ( Horario horario : medicodto.getHorarios() ){
            horario.setMedicoInstituto(savedMedico_Instituto);
            horarioService.createHorario(horario);
            horariosCreados.add(horario);
        }
        
        //Asignando los horarios creados al medico_instituto
        savedMedico_Instituto.setHorarios(horariosCreados);
        
        //Llamo al servicio para que cree los datos en la bd
        medicoService.createMedico(savedMedico, savedMedico_Instituto);
        
        return ResponseEntity.ok(createdMedico);
    }
    
    
    @GetMapping ("medico/load/{id}")
    @ResponseBody
    public ResponseEntity<GetMedicoDTO> getMedicoById(@PathVariable Long id){
        
        //Obtengo los datos por id
        Medico medico = medicoService.getMedicoById(id);
        
        //Creo el dto
        GetMedicoDTO medicodto = new GetMedicoDTO();
        medicodto.setId(medico.getId());
        medicodto.setNombre(medico.getNombre());
        medicodto.setDni(medico.getDni());
        medicodto.setMail(medico.getMail());
        medicodto.setDireccion(medico.getDireccion());
        medicodto.setTel(medico.getTel());
        medicodto.setMatricula(medico.getMatricula());
        
        //Obetener la lista de institutos del medico
        List<InstitutoDTO> institutosDTO = new ArrayList<>();
        
        for ( Medico_Instituto medicoInstituto : medico.getMedicoInstituto() ){
            
            Instituto instituto = medicoInstituto.getInstituto();
            
            //Crear objeto institutoDTO y mapear los datos del instituto
            InstitutoDTO institutoDTO = new InstitutoDTO();
            institutoDTO.setId(instituto.getId());
            institutoDTO.setNombre(instituto.getNombre());
            institutoDTO.setDireccion(instituto.getDireccion());
            
            //Obetengo la lista de horarios del instituto
            List<HorarioDTO> horariosDTO = new ArrayList<>();
            for ( Horario horario : medicoInstituto.getHorarios() ){
                
                //guardamos en List<Horario>
                HorarioDTO horarioDTO = new HorarioDTO();
                horarioDTO.setId(horario.getId());
                horarioDTO.setDia(horario.getDia());
                horarioDTO.setInicio(horario.getInicio());
                horarioDTO.setFin(horario.getFin());
                horarioDTO.setIntervalo(horario.getIntervalo());
                
                //agregamos a list<horario>
                horariosDTO.add(horarioDTO);
            }
            //agrego los horarios en institutodto
            institutoDTO.setHorarios(horariosDTO);
            
            //agrego el instituto al listado de institutos de medicodto
            institutosDTO.add(institutoDTO);
            
        }
        
        //guardo el listado de institutos en medicodto
        medicodto.setInstitutos(institutosDTO);
        
        return ResponseEntity.ok(medicodto);
    }
    
}
