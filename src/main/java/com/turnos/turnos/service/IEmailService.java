package com.turnos.turnos.service;

import org.springframework.mail.SimpleMailMessage;

public interface IEmailService {
    
    
    public String sendEmail( SimpleMailMessage email );
}
