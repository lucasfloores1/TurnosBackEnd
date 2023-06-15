package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.NuevoTurnoDTO;
import com.turnos.turnos.model.Turno;
import com.turnos.turnos.service.impl.EmailServiceImpl;
import com.turnos.turnos.service.impl.EstudioServiceImpl;
import com.turnos.turnos.service.impl.InstitutoServiceImpl;
import com.turnos.turnos.service.impl.MedicoServiceImpl;
import com.turnos.turnos.service.impl.ObraSocialServiceImpl;
import com.turnos.turnos.service.impl.PacienteServiceImpl;
import com.turnos.turnos.service.impl.PlanServiceImpl;
import com.turnos.turnos.service.impl.TurnoServiceImpl;
import com.turnos.turnos.service.impl.UserServiceImpl;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin( origins = "http://localhost:4200" )
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
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger(EmailServiceImpl.class);
    
    @GetMapping( "turno/load" )
    @ResponseBody
    public List<Turno> loadTurno(){
        return turnoService.getTurnos();
    }
    
    @PostMapping( "/turno/create" )
    @ResponseBody
    public ResponseEntity<?> createTurno( @RequestBody NuevoTurnoDTO turnoDTO ) {
        
        //creo el turno
        Turno createdTurno = new Turno();
        createdTurno.setUser(userService.getUserById(turnoDTO.getUserId()));
        createdTurno.setFecha(turnoDTO.getFecha());
        createdTurno.setCargado(turnoDTO.isCargado());
        createdTurno.setConfirmado(turnoDTO.isConfirmado());
        createdTurno.setCancelado(turnoDTO.isCancelado());
        
        //Establezco los datos por las id del dto
        createdTurno.setMedico( medicoService.getMedicoById( turnoDTO.getIdMedico() ) );
        createdTurno.setInstituto( institutoService.getInstitutoById( turnoDTO.getIdInstituto() ) );
        createdTurno.setPaciente( pacienteService.getPacienteById( turnoDTO.getIdPaciente() ) );
        createdTurno.setObraSocial( obraSocialService.getObraSocialById( turnoDTO.getIdObraSocial() ) );
        createdTurno.setPlan( planService.getPlanById( turnoDTO.getIdPlan() ) );
        createdTurno.setEstudio( estudioService.getEstudioById( turnoDTO.getIdEstudio() ) );
        
        //Guardo el nuevo Turno en la bd
        turnoService.createTurno(createdTurno);
        
        sendNewTurnoEmail(createdTurno);
        
        return ResponseEntity.ok(createdTurno);
    }
    public String sendNewTurnoEmail ( Turno turno ){
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d 'de' MMMM yyyy 'a las' HH:mm", new Locale("es"));

        SimpleMailMessage email =  new SimpleMailMessage();

        email.setTo(turno.getPaciente().getMail());
        email.setSubject("Nuevo turno para el Dr."+turno.getMedico().getNombre());
        email.setText(
            "Tienes un turno para un/a "
            + turno.getEstudio().getNombre()
            + " el "
            + turno.getFecha().format(formato)
            + " con el Dr."
            + turno.getMedico().getNombre()
            + " en "
            + turno.getInstituto().getNombre()
            + " en "
            + turno.getInstituto().getDireccion()
        );
                
        return emailServiceImpl.sendEmail(email);
    }
    
    @GetMapping ( "/turno/load/{id}" )
    @ResponseBody
    public ResponseEntity<Turno> getTurnoById( @PathVariable Long id ){
        Turno turno = turnoService.getTurnoById(id);
        return ResponseEntity.ok(turno);
    }
    
    @DeleteMapping( "/turno/delete/{id}" )
    public void deleteTurno( @PathVariable Long id ){
        logger.info("ejecutando cancel");
        turnoService.deleteTurno(id);
    }
    
    @PutMapping( "/turno/update/{id}" )
    public ResponseEntity<Turno> updateTurno( @PathVariable Long id, @RequestBody Turno toUpdateTurno ){
        
        return turnoService.updateTurno(id, toUpdateTurno);
    
    };
    
    @GetMapping( "/turno/medico/{id}" )
    @ResponseBody
    public List<Turno> loadTurnosByMedico( @PathVariable Long id ){
        return turnoService.getTurnosByMedico(id);
    }
    
    @GetMapping( "/turno/user/{id}" )
    @ResponseBody
    public List<Turno> loadTurnosByUser( @PathVariable Long id ){
    
        return turnoService.getTurnosByUser(id);
        
    }
    
    @GetMapping ( "/user/confirm-turno/{id}" )
    public RedirectView confirmTurno ( @PathVariable Long id  ){
        
        Turno turno = turnoService.getTurnoById(id);
        
        turno.setConfirmado(true);
        
        turnoService.updateTurno(turno.getId(), turno);
        
        return new RedirectView("http://localhost:4200/confirm-turno");
    }
    
    @GetMapping ( "/user/cancel-turno/{id}" )
    public RedirectView cancelTurno ( @PathVariable Long id  ) {
        
        Turno turno = turnoService.getTurnoById(id);
        
        turno.setCancelado(true);
        
        turnoService.updateTurno(turno.getId(), turno);
        
        return new RedirectView("http://localhost:4200/cancel-turno");
    }
    
}
