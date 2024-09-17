package com.spring.dockerturorial1.controller;

import com.spring.dockerturorial1.model.EmailDetailsRequest;
import com.spring.dockerturorial1.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // Sending a simple Email
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetailsRequest details)
    {
        return emailService.sendSimpleMail(details);
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetailsRequest details)
    {
        return emailService.sendMailWithAttachment(details);
    }

    @PostMapping("/sendMailWithThymeleafTemplate")
    public String sendMailWithThymeleafTemplate(
            @RequestBody EmailDetailsRequest details)
    {
        return emailService.sendEmailWithThymeleafTemplate(details);
    }
}
