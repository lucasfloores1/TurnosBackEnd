
package com.turnos.turnos.controller;

import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.service.impl.ObraSocialServiceImpl;
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
public class ObraSocialController {
    
    @Autowired
    private ObraSocialServiceImpl obraSocialService;
    
    @GetMapping( "/obraSocial/load" )
    @ResponseBody
    public List<ObraSocial> loadObraSocial(){
        return obraSocialService.getObraSocials();
    }
    
    @PostMapping( "/obraSocial/create" )
    public ResponseEntity<ObraSocial> createObraSocial( @RequestBody ObraSocial obraSocial ) {
        ResponseEntity<ObraSocial> response;
        
        ObraSocial createdObraSocial = obraSocialService.createObraSocial(obraSocial).getBody();
        
        if ( createdObraSocial != null ){
            
        response = ResponseEntity.ok(createdObraSocial);
            
        }else {
            
            response = ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
            
        }
        return response;
    }  
    
    @DeleteMapping( "/obraSocial/delete/{id}" )
    public void deleteObraSocial( @PathVariable Long id ){
        obraSocialService.deleteObraSocial(id);
    }
    
    @PutMapping( "/obraSocial/update/{id}" )
    public ResponseEntity<ObraSocial> updateObraSocial( @PathVariable Long id, @RequestBody ObraSocial toUpdateObraSocial ){
        
        return obraSocialService.updateObraSocial(id, toUpdateObraSocial);
    
    };
    
}
