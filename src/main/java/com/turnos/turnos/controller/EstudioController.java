package com.turnos.turnos.controller;

import com.turnos.turnos.model.Estudio;
import com.turnos.turnos.service.impl.EstudioServiceImpl;
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
public class EstudioController {
    
    @Autowired
    private EstudioServiceImpl estudioService;
    
    @GetMapping( "/estudio/load" )
    @ResponseBody
    public List<Estudio> loadEstudio(){
        return estudioService.getEstudios();
    }
    
    @GetMapping( "/estudio/load/{id}" )
    @ResponseBody
    public ResponseEntity<Estudio> loadEstudio(@PathVariable Long id){
        Estudio estudio = estudioService.getEstudioById(id);
        return ResponseEntity.ok(estudio);
    }
    
    @PostMapping( "/estudio/create" )
    @ResponseBody
    public ResponseEntity<Estudio> createEstudio( @RequestBody Estudio estudio ) {
        ResponseEntity<Estudio> response;
        
        Estudio createdEstudio = estudioService.createEstudio(estudio).getBody();
        
        if ( createdEstudio != null ){
            
        response = ResponseEntity.ok(createdEstudio);
            
        }else {
            
            response = ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
            
        }
        return response;
    }  
    
    @DeleteMapping( "/estudio/delete/{id}" )
    public void deleteEstudio( @PathVariable Long id ){
        estudioService.deleteEstudio(id);
    }
    
    @PutMapping( "/estudio/update/{id}" )
    public ResponseEntity<Estudio> updateEstudio( @PathVariable Long id, @RequestBody Estudio toUpdateEstudio ){
        
        return estudioService.updateEstudio(id, toUpdateEstudio);
    
    };
    
}