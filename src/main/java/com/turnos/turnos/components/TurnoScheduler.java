package com.turnos.turnos.components;

import com.turnos.turnos.model.Turno;
import com.turnos.turnos.service.impl.EmailServiceImpl;
import com.turnos.turnos.service.impl.TurnoServiceImpl;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TurnoScheduler {
    
    @Autowired
    private TurnoServiceImpl turnoService;
    
    @Autowired
    private EmailServiceImpl emailService;
    
    private final String baseUrl = "http://localhost:8080/user";
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger(EmailServiceImpl.class);
    
    @Scheduled( cron = "0 0 9 * * ?" )
    public void sendReminder(){
        
        logger.info("Ejecutando sendReminder()");
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d 'de' MMMM yyyy 'a las' HH:mm", new Locale("es"));
        
        List<Turno> turnos = turnoService.getTurnosForTomorrow();
        
        logger.info("Número de turnos encontrados: " + turnos.size());
        
        for ( Turno turno : turnos ){
            SimpleMailMessage email = new SimpleMailMessage();
            
            email.setTo(turno.getPaciente().getMail());
            email.setSubject( "Recordatorio turno con Dr. "+turno.getMedico().getNombre() );
            email.setText("El dia "+ turno.getFecha().format(formato) +" tienes un turno con el Dr. "+turno.getMedico().getNombre()+" en "+turno.getInstituto().getNombre()+" en "+turno.getInstituto().getDireccion()+"\n\n"
                    +"Por favor confirma tu asistencia o cancela el turno con los siguientes enlaces:\n\n"
                    +"<a href=\"" + baseUrl + "/confirm-turno/" + turno.getId()+ "\">Confirmar Turno</a>\n\n"
                    +"<a href=\"" + baseUrl + "/cancel-turno/" + turno.getId()+ "\">Cancelar Turno</a>\n\n"
            );
            logger.info("Enviando un mail()");
            emailService.sendEmail(email);
        }
    }
    
}
