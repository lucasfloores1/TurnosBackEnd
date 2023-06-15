package com.turnos.turnos.service.impl;

import com.turnos.turnos.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    JavaMailSender javaMailSender;
    
    @Override
    public String sendEmail(SimpleMailMessage email) {
        
        javaMailSender.send(email);
        
        return "Email enviado con éxito";
    }
    
}
