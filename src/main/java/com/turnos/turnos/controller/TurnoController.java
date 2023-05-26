
package com.turnos.turnos.controller;


import com.turnos.turnos.DTO.NuevoTurnoDTO;
import com.turnos.turnos.model.Turno;
import com.turnos.turnos.service.impl.EstudioServiceImpl;
import com.turnos.turnos.service.impl.InstitutoServiceImpl;
import com.turnos.turnos.service.impl.MedicoServiceImpl;
import com.turnos.turnos.service.impl.ObraSocialServiceImpl;
import com.turnos.turnos.service.impl.PacienteServiceImpl;
import com.turnos.turnos.service.impl.PlanServiceImpl;
import com.turnos.turnos.service.impl.TurnoServiceImpl;
import java.util.List;
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
public class TurnoController {
    
    @Autowired
    private TurnoServiceImpl turnoService;
    @Autowired
    private MedicoServiceImpl medicoService;
    @Autowired
    private InstitutoServiceImpl institutoService;
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private ObraSocialServiceImpl obraSocialService;
    @Autowired
    private PlanServiceImpl planService;
    @Autowired
    private EstudioServiceImpl estudioService;
    
    @GetMapping( "/turno/load" )
    @ResponseBody
    public List<Turno> loadTurno(){
        return turnoService.getTurnos();
    }
    
    @PostMapping( "/turno/create" )
    @ResponseBody
    public ResponseEntity<?> createTurno( @RequestBody NuevoTurnoDTO turnoDTO ) {
        
        //creo el turno
        Turno createdTurno = new Turno();
        createdTurno.setFecha(turnoDTO.getFecha());
        createdTurno.setCargado(turnoDTO.isCargado());
        createdTurno.setConfirmado(turnoDTO.isConfirmado());
        
        //Establezco los datos por las id del dto
        createdTurno.setMedico( medicoService.getMedicoById( turnoDTO.getIdMedico() ) );
        createdTurno.setInstituto( institutoService.getInstitutoById( turnoDTO.getIdInstituto() ) );
        createdTurno.setPaciente( pacienteService.getPacienteById( turnoDTO.getIdPaciente() ) );
        createdTurno.setObraSocial( obraSocialService.getObraSocialById( turnoDTO.getIdObraSocial() ) );
        createdTurno.setPlan( planService.getPlanById( turnoDTO.getIdPlan() ) );
        createdTurno.setEstudio( estudioService.getEstudioById( turnoDTO.getIdEstudio() ) );
        
        //Guardo el nuevo Turno en la bd
        turnoService.createTurno(createdTurno);
        
        return ResponseEntity.ok(createdTurno);
    }
    
    @GetMapping ( "turno/load/{id}" )
    @ResponseBody
    public ResponseEntity<Turno> getTurnoById( @PathVariable Long id ){
        Turno turno = turnoService.getTurnoById(id);
        return ResponseEntity.ok(turno);
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
