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
import com.turnos.turnos.service.impl.UserServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@RestController
//@CrossOrigin( origins = "http://localhost:4200" )
@CrossOrigin( origins = "https://turnomed.up.railway.app/" )
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
    private JavaMailSender javaMailSender;
    
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
        createdTurno.setUser(userService.getUserById(turnoDTO.getUserId()));
        createdTurno.setFecha(turnoDTO.getFecha());
        createdTurno.setCargado(turnoDTO.isCargado());
        createdTurno.setConfirmado(turnoDTO.isConfirmado());
        createdTurno.setCancelado(turnoDTO.isCancelado());
        
        //Establezco los turno por las id del dto
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
    
    @GetMapping ( "/turno/load/{id}" )
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
    
    @PostMapping( "/turno/informe/{id}" )
    public ResponseEntity<?> sendInforme( @PathVariable Long id, @RequestParam( "files" ) List<MultipartFile> files ){
        try{
            Turno turno = turnoService.getTurnoById(id);
            sendInformeEmail(files, turno);
            turno.setCargado(true);
            turnoService.updateTurno(turno.getId(), turno);
            return ResponseEntity.ok(turnoService.getTurnoById(id));
        }catch(Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    private String sendNewTurnoEmail(Turno turno) {
        try {
            // Cargar la plantilla HTML
            ClassPathResource resource = new ClassPathResource("templates/new-turno-email.html");
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            StringBuilder templateBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                templateBuilder.append(line).append("\n");
            }

            String template = templateBuilder.toString();

            // Reemplazar variables en la plantilla HTML con turno del turno
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("d 'de' MMMM yyyy 'a las' HH:mm", new Locale("es"));
            String emailContent = template
                .replace("[PACIENTE_NOMBRE]", turno.getPaciente().getNombre())
                .replace("[ESTUDIO_NOMBRE]", turno.getEstudio().getNombre())
                .replace("[TURNO_FECHA]", turno.getFecha().format(formato))
                .replace("[MEDICO_NOMBRE]", turno.getMedico().getNombre())
                .replace("[INSTITUTO_NOMBRE]", turno.getInstituto().getNombre())
                .replace("[INSTITUTO_DIRECCION]", turno.getInstituto().getDireccion());

            // Crear el mensaje de correo electrónico
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(turno.getPaciente().getMail());
            helper.setSubject("Nuevo turno para el Dr. " + turno.getMedico().getNombre());
            helper.setText(emailContent, true);

            // Enviar el correo electrónico
            javaMailSender.send(message);

            return "Correo electrónico enviado con éxito";
        } catch (Exception ex) {
            return "Error al enviar el correo electrónico: " + ex.getMessage();
        }
    }
    
    
    private void sendInformeEmail(List<MultipartFile> files, Turno turno) throws MessagingException, IOException {
        // Cargar el contenido del template HTML
        ClassPathResource resource = new ClassPathResource("templates/informe-turno-email.html");
        String templateContent = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        // Reemplazar variables en el contenido del template con turno del turno
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d 'de' MMMM yyyy 'a las' HH:mm", new Locale("es"));
        String emailContent = templateContent
                .replace("[PACIENTE_NOMBRE]", turno.getPaciente().getNombre())
                .replace("[ESTUDIO_NOMBRE]", turno.getEstudio().getNombre())
                .replace("[TURNO_FECHA]", turno.getFecha().format(formato))
                .replace("[MEDICO_NOMBRE]", turno.getMedico().getNombre())
                .replace("[INSTITUTO_NOMBRE]", turno.getInstituto().getNombre())
                .replace("[INSTITUTO_DIRECCION]", turno.getInstituto().getDireccion());

        // Crear el mensaje de correo electrónico
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(turno.getPaciente().getMail());
        helper.setSubject("Informe de turno con el/la Dr. " + turno.getMedico().getNombre());
        helper.setText(emailContent, true);

        // Adjuntar los archivos al correo electrónico
        for (MultipartFile file : files) {
            InputStreamSource inputStreamSource = new ByteArrayResource(file.getBytes());
            helper.addAttachment(file.getOriginalFilename(), inputStreamSource, file.getContentType());
        }

        // Enviar el correo electrónico
        javaMailSender.send(message);
    }

}
