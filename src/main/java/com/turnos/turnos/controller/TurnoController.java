
package com.turnos.turnos.controller;

import com.turnos.turnos.model.Turno;
import com.turnos.turnos.service.impl.TurnoServiceImpl;
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
public class TurnoController {
    
    @Autowired
    private TurnoServiceImpl turnoService;
    
    @GetMapping( "/turno/load" )
    @ResponseBody
    public List<Turno> loadTurno(){
        return turnoService.getTurnos();
    }
    
    @PostMapping( "/turno/create" )
    public ResponseEntity<Turno> createTurno( @RequestBody Turno turno ) {
        ResponseEntity<Turno> response;
        
        Turno createdTurno = turnoService.createTurno(turno).getBody();
        
        if ( createdTurno != null ){
            
        response = ResponseEntity.ok(createdTurno);
            
        }else {
            
            response = ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
            
        }
        return response;
    }  
    
    @DeleteMapping( "/turno/delete/{id}" )
    public void deleteTurno( @PathVariable Long id ){
        turnoService.deleteTurno(id);
    }
    
    @PutMapping( "/turno/update/{id}" )
    public ResponseEntity<Turno> updateTurno( @PathVariable Long id, @RequestBody Turno toUpdateTurno ){
        
        return turnoService.updateTurno(id, toUpdateTurno);
    
    };
    
}
