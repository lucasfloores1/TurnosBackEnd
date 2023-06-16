package com.turnos.turnos.components;

import com.turnos.turnos.model.Turno;
import com.turnos.turnos.service.impl.TurnoServiceImpl;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class TurnoScheduler {

    @Autowired
    private TurnoServiceImpl turnoService;

    @Autowired
    private JavaMailSender emailSender;

    private final String baseUrl = "http://localhost:8080/user";

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendReminder() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d 'de' MMMM yyyy 'a las' HH:mm", new Locale("es"));
        List<Turno> turnos = turnoService.getTurnosForTomorrow();

        for (Turno turno : turnos) {
            try {
                // Cargar la plantilla HTML
                ClassPathResource resource = new ClassPathResource("templates/reminder-email.html");
                InputStream inputStream = resource.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                StringBuilder templateBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    templateBuilder.append(line).append("\n");
                }

                String template = templateBuilder.toString();

                // Reemplazar variables en la plantilla HTML con datos del turno
                String emailContent = template
                        .replace("[PACIENTE_NOMBRE]", turno.getPaciente().getNombre())
                        .replace("[TURNO_FECHA]", turno.getFecha().format(formato))
                        .replace("[MEDICO_NOMBRE]", turno.getMedico().getNombre())
                        .replace("[INSTITUTO_NOMBRE]", turno.getInstituto().getNombre())
                        .replace("[INSTITUTO_DIRECCION]", turno.getInstituto().getDireccion())
                        .replace("[CONFIRMAR_TURNO_URL]", baseUrl + "/confirm-turno/" + turno.getId())
                        .replace("[CANCELAR_TURNO_URL]", baseUrl + "/cancel-turno/" + turno.getId());

                // Crear el mensaje de correo electrónico
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
                helper.setTo(turno.getPaciente().getMail());
                helper.setSubject("Recordatorio turno con Dr. " + turno.getMedico().getNombre());
                helper.setText(emailContent, true);

                // Enviar el correo electrónico
                emailSender.send(message);
            } catch (Exception ex) {
                // Manejar cualquier excepción que ocurra durante el envío del correo electrónico
            }
        }
    }
}
