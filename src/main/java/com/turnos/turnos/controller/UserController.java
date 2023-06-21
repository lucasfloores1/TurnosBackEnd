package com.turnos.turnos.controller;

import com.turnos.turnos.DTO.NuevoUserDTO;
import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Plan;
import com.turnos.turnos.model.User;
import com.turnos.turnos.service.impl.ObraSocialServiceImpl;
import com.turnos.turnos.service.impl.PlanServiceImpl;
import com.turnos.turnos.service.impl.UserServiceImpl;
import jakarta.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UserController {
    
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private ObraSocialServiceImpl obraSocialService;
    
    @Autowired
    private PlanServiceImpl planService;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @GetMapping ( "/user/load" )
    @ResponseBody
    public List<User> getUsers(){
        return userService.getUsers();
    }
    
    @DeleteMapping( "/user/delete/{id}" )
    public void deleteUser( @PathVariable Long id ){
        userService.deleteUser(id);
    }
    
    @PutMapping( "/user/update/{id}" )
    public ResponseEntity<User> updateUser( @PathVariable Long id, @RequestBody User toUpdateUser ){ 
        return userService.updateUser(id, toUpdateUser);
    };
    
    @GetMapping( "/user/load/{id}" )
    @ResponseBody
    public ResponseEntity<User> getUserById (@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping( "/user/create" )
    @ResponseBody
    public ResponseEntity<User> createUser( @RequestBody NuevoUserDTO userDTO ) {
        User user = new User();
        
        user.setNombre(userDTO.getNombre());
        user.setTel(userDTO.getTel());
        user.setEmail(userDTO.getEmail());
        user.setEnable(false);
        user.setUsername(userDTO.getUsername());
        user.setVerification(generateVerificationCode());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User createdUser = userService.createUser(user);
        
        if ( createdUser != null ){
            //Creo particular
            ObraSocial obraSocial = new ObraSocial();
            obraSocial.setNombre("Particular");
            obraSocial.setDireccion("Calle y número");
            obraSocial.setUser(createdUser);
            ObraSocial savedObraSocial = obraSocialService.createObraSocial(obraSocial);
            //Creo plan
            Plan plan = new Plan();
            plan.setNombre("Particular");
            plan.setObraSocial(savedObraSocial);
            Plan createdPlan = planService.createPlan(plan);
            //Añado el plan y guardo
            savedObraSocial.getPlanes().add(createdPlan);
            obraSocialService.createObraSocial(savedObraSocial);
            
        }else {
            
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
            
        }
        sendVerificationEmail(createdUser);
        return ResponseEntity.ok(createdUser);
    }
    
    @GetMapping( "/user/verify-account/{id}/{code}" )
    @ResponseBody
    public ResponseEntity<User> verifyUserAccount (@PathVariable Long id, @PathVariable String code){
        User user = userService.getUserById(id);
        if (user != null){
            if (user.getVerification().equals(code)){
                user.setEnable(true);
                User savedUser = userService.createUser(user);
                return ResponseEntity.ok(savedUser);
            } else {
                return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
            }
        } else {
         return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
        }
    }
    
    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Caracteres permitidos para el código

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length()); // Índice aleatorio dentro del rango de caracteres
            char character = characters.charAt(index); // Obtengo el carácter en el índice aleatorio
            codeBuilder.append(character);
        }

        return codeBuilder.toString();
    }
    
        private String sendVerificationEmail(User user) {
        try {
            // Cargar la plantilla HTML
            ClassPathResource resource = new ClassPathResource("templates/verification-code-email.html");
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            StringBuilder templateBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                templateBuilder.append(line).append("\n");
            }

            String template = templateBuilder.toString();

            // Reemplazar variables en la plantilla HTML con turno del turno
            String emailContent = template
                .replace("[USER_NOMBRE]", user.getNombre())
                .replace("[VERIFICATION_CODE]", user.getVerification());

            // Crear el mensaje de correo electrónico
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Verifica tu cuenta en TurnosApp");
            helper.setText(emailContent, true);

            // Enviar el correo electrónico
            javaMailSender.send(message);

            return "Correo electrónico enviado con éxito";
        } catch (Exception ex) {
            return "Error al enviar el correo electrónico: " + ex.getMessage();
        }
    }
    
}
