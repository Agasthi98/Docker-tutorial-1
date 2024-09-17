package com.spring.dockerturorial1.service;

import com.spring.dockerturorial1.model.EmailDetailsRequest;

import java.util.Map;

public interface EmailService {
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetailsRequest details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetailsRequest details);


     String sendEmailWithThymeleafTemplate(EmailDetailsRequest details);

}
