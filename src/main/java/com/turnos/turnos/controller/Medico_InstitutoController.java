package com.turnos.turnos.controller;

import com.turnos.turnos.model.Medico_Instituto;
import com.turnos.turnos.repository.Medico_InstitutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "http://localhost:4200/" )
public class Medico_InstitutoController {
    
    @Autowired
    Medico_InstitutoRepository medicoInstitutoRepository;
    
    @GetMapping( "/medicoInstituto" )
    @ResponseBody
    public List<Medico_Instituto> getMedicosInstituto(){
        return medicoInstitutoRepository.findAll();
    }
    
    @GetMapping( "/medicoInstituto/{id}" )
    @ResponseBody
    public ResponseEntity<?> getMedicosInstitutoById(@PathVariable Long id){
        Medico_Instituto response = medicoInstitutoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(response);
    }
    
}
