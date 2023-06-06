package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.NuevaObraSocialDTO;
import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Plan;
import com.turnos.turnos.service.impl.ObraSocialServiceImpl;
import com.turnos.turnos.service.impl.PlanServiceImpl;
import com.turnos.turnos.service.impl.UserServiceImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PlanServiceImpl planService;
    @Autowired
    private UserServiceImpl userService;
    
    @GetMapping( "/obraSocial/load" )
    @ResponseBody
    public List<ObraSocial> loadObraSocial(){
        return obraSocialService.getObraSocials();
    }
    
    @GetMapping( "obraSocial/user/load" )
    @ResponseBody
    public List<ObraSocial> loadObrasSocialesByUser( @PathVariable Long id ){
        return obraSocialService.getObrasSocialesByUser(id);
    }
    
    @PostMapping( "/obraSocial/create" )
    @ResponseBody
    public ResponseEntity<?> createObraSocial( @RequestBody NuevaObraSocialDTO obraSocialdto ) {
        //creando nueva obra social
        ObraSocial createdObraSocial = new ObraSocial();
        createdObraSocial.setUser( userService.getUserById( obraSocialdto.getUserId()) );
        createdObraSocial.setNombre(obraSocialdto.getNombre());
        createdObraSocial.setDireccion(obraSocialdto.getDireccion());
        
        //guardando
        ObraSocial savedObraSocial = obraSocialService.createObraSocial(createdObraSocial);
        
        //Creando Planes
        Set<Plan> planesCreados = new HashSet<>();
        for ( Plan plan : obraSocialdto.getPlanes() ){
            Plan newplan = new Plan();
            newplan.setNombre(plan.getNombre());
            newplan.setObraSocial(savedObraSocial);
            planService.createPlan(newplan);
            planesCreados.add(newplan);
        }
        
        //Agregando los planes creados
        savedObraSocial.setPlanes(planesCreados);
        
        return ResponseEntity.ok(savedObraSocial);
    }  
    
    @DeleteMapping( "/obraSocial/delete/{id}" )
    public void deleteObraSocial( @PathVariable Long id ){
        obraSocialService.deleteObraSocial(id);
    }
    
    @PutMapping( "/obraSocial/update/{id}" )
    public ResponseEntity<ObraSocial> updateObraSocial( @PathVariable Long id, @RequestBody ObraSocial toUpdateObraSocial ){
        
        return obraSocialService.updateObraSocial(id, toUpdateObraSocial);
    
    };
    
    @GetMapping ( "/obraSocial/planes/{id}" )
    public ResponseEntity<List> getPlanesByObraSocial(@PathVariable Long id){
        List<Plan> planes = planService.getPlanesByObraSocial(id);                
        return ResponseEntity.ok(planes);
    }
    
}
