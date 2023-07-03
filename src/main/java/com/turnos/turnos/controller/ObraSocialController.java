package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.GetObraSocialDTO;
import com.turnos.turnos.DTO.NuevaObraSocialDTO;
import com.turnos.turnos.DTO.PlanDTO;
import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Plan;
import com.turnos.turnos.service.impl.ObraSocialServiceImpl;
import com.turnos.turnos.service.impl.PlanServiceImpl;
import com.turnos.turnos.service.impl.UserServiceImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
//@CrossOrigin( origins = "http://localhost:4200/" )
@CrossOrigin( origins = "https://turnomed.up.railway.app/" )
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
    
    @GetMapping( "/obraSocial/user/{id}" )
    @ResponseBody
    public ResponseEntity<Set<GetObraSocialDTO>> loadObrasSocialesByUser( @PathVariable Long id ){
        List<ObraSocial> obrasSociales = obraSocialService.getObrasSocialesByUser(id);
        
        Set<GetObraSocialDTO> response = new HashSet<>();
        for ( ObraSocial obra : obrasSociales ){
            GetObraSocialDTO obraSocial = new GetObraSocialDTO();
            obraSocial.setId(obra.getId());
            obraSocial.setNombre(obra.getNombre());
            obraSocial.setDireccion(obra.getDireccion());
            obraSocial.setPlanes(planService.getPlanesByObraSocial(obra.getId()));
            response.add(obraSocial);
        }
        return ResponseEntity.ok(response);
    }
    
    /*@PostMapping( "/obraSocial/create" )
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
        for ( PlanDTO plan : obraSocialdto.getPlanes() ){
            Plan newplan = new Plan();
            newplan.setNombre(plan.getNombre());
            newplan.setObraSocial(savedObraSocial);
            planService.createPlan(newplan);
            planesCreados.add(newplan);
        }
        
        //Agregando los planes creados
        savedObraSocial.setPlanes(planesCreados);
        
        return ResponseEntity.ok(savedObraSocial);
    }*/  
    
    @DeleteMapping( "/obraSocial/delete/{id}" )
    public void deleteObraSocial( @PathVariable Long id ){
        obraSocialService.deleteObraSocial(id);
    }
    
    @PutMapping( "/obraSocial/update/{id}" )
    public ResponseEntity<ObraSocial> updateObraSocial( @PathVariable Long id, @RequestBody ObraSocial toUpdateObraSocial ){
        
        return obraSocialService.updateObraSocial(id, toUpdateObraSocial);
    
    };
    
    @GetMapping ( "/obraSocial/planes/{id}" )
    public ResponseEntity<Set<PlanDTO>> getPlanesByObraSocial(@PathVariable Long id){
        Set<PlanDTO> planes = planService.getPlanesByObraSocial(id);                
        return ResponseEntity.ok(planes);
    }
    
    @GetMapping( "/obraSocial/load/{id}" )
    @ResponseBody
    public ResponseEntity<GetObraSocialDTO> getObraSocialById ( @PathVariable Long id ){
        
        ObraSocial obraSocial = obraSocialService.getObraSocialById(id);
        GetObraSocialDTO obraSocialDTO = new GetObraSocialDTO();
        Set<PlanDTO> planes = planService.getPlanesByObraSocial(id);
        
        obraSocialDTO.setId(obraSocial.getId());
        obraSocialDTO.setNombre(obraSocial.getNombre());
        obraSocialDTO.setDireccion(obraSocial.getDireccion());
        obraSocialDTO.setPlanes(planes);
        
        return ResponseEntity.ok(obraSocialDTO);
    }
    
    @PostMapping("/obraSocial/create")
    @ResponseBody
    public ResponseEntity<?> createOrUpdateObraSocial(@RequestBody NuevaObraSocialDTO obraSocialdto) {
        ObraSocial obraSocial;

        if (obraSocialdto.getId() == 0) {
            // ID es 0, indicando una creación de ObraSocial
            obraSocial = new ObraSocial();
            obraSocial.setUser(userService.getUserById(obraSocialdto.getUserId()));
        } else {
            // ID distinto de 0, indica que ya existe una ObraSocial con ese ID
            obraSocial = obraSocialService.getObraSocialById(obraSocialdto.getId());

            if (obraSocial == null) {
                // No se encontró una ObraSocial con ese ID
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not ObraSocial was found");
            }
        }

        // Actualizar los datos de la ObraSocial
        obraSocial.setNombre(obraSocialdto.getNombre());
        obraSocial.setDireccion(obraSocialdto.getDireccion());

        // Actualizar o crear los planes
        Set<Plan> planesActualizados = new HashSet<>();
        for (PlanDTO planDTO : obraSocialdto.getPlanes()) {
            Plan plan;

            if (planDTO.getId() != 0) {
                // ID distinto de 0, indica que ya existe un Plan con ese ID
                plan = planService.getPlanById(planDTO.getId());

                if (plan == null) {
                    // No se encontró un Plan con ese ID
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not Plan was found");
                }
            } else {
                // ID es 0, indicando una creación de Plan
                plan = new Plan();
                plan.setObraSocial(obraSocial);
            }

            // Actualizar los datos del Plan
            plan.setNombre(planDTO.getNombre());

            // Agregar el Plan actualizado o creado
            planesActualizados.add(plan);
        }

        // Asignar los planes actualizados o recién creados a la ObraSocial
        obraSocial.setPlanes(planesActualizados);

        // Guardar la ObraSocial en la base de datos
        ObraSocial savedObraSocial = obraSocialService.createObraSocial(obraSocial);

        return ResponseEntity.ok(savedObraSocial);
    }
}
