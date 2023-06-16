package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.NuevoInstitutoDTO;
import com.turnos.turnos.model.Instituto;
import com.turnos.turnos.service.impl.InstitutoServiceImpl;
import com.turnos.turnos.service.impl.UserServiceImpl;
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
public class InstitutoController {
    
    @Autowired
    private InstitutoServiceImpl institutoService;
    @Autowired
    private UserServiceImpl userService;
    
    @GetMapping( "/instituto/load" )
    @ResponseBody
    public List<Instituto> loadInstituto(){
        return institutoService.getInstitutos();
    }
    
    @GetMapping( "/instituto/load/{id}" )
    @ResponseBody
    public ResponseEntity<Instituto> loadInstituto(@PathVariable Long id){
        Instituto instituto = institutoService.getInstitutoById(id);
        return ResponseEntity.ok(instituto);
    }
    
    @GetMapping( "/instituto/user/{id}" )
    @ResponseBody
    public List<Instituto> getInstitutosByUser ( @PathVariable Long id ){
    
        return institutoService.getInstitutosByUser(id);
    }
    
    @PostMapping( "/instituto/create" )
    @ResponseBody
    public ResponseEntity<?> createInstituto( @RequestBody NuevoInstitutoDTO institutoDTO ) {
        
        Instituto createdInstituto = new Instituto();
        createdInstituto.setUser(userService.getUserById(institutoDTO.getUserId()));
        createdInstituto.setNombre(institutoDTO.getNombre());
        createdInstituto.setDireccion(institutoDTO.getDireccion());
        createdInstituto.setCuit(institutoDTO.getCuit());
        
        //Guardo
        return institutoService.createInstituto(createdInstituto);
    }  
    
    @DeleteMapping( "/instituto/delete/{id}" )
    public void deleteInstituto( @PathVariable Long id ){
        institutoService.deleteInstituto(id);
    }
    
    @PutMapping( "/instituto/update/{id}" )
    public ResponseEntity<Instituto> updateInstituto( @PathVariable Long id, @RequestBody Instituto toUpdateInstituto ){
        
        return institutoService.updateInstituto(id, toUpdateInstituto);
    
    };
    
}