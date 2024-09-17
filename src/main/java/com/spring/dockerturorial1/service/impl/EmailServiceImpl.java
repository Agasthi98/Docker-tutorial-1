package com.spring.dockerturorial1.service.impl;

import com.spring.dockerturorial1.model.EmailDetailsRequest;
import com.spring.dockerturorial1.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    private final SpringTemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }


    @Override
    public String sendSimpleMail(EmailDetailsRequest details) {
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetailsRequest details) {

        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    Objects.requireNonNull(file.getFilename()), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

    @Override
    public String sendEmailWithThymeleafTemplate(EmailDetailsRequest details) {
        try {

            Map<String,Object> templateModel = createTransferEmail();
            // Create a MimeMessage
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());

            // Create context for Thymeleaf
            Context context = new Context();
            context.setVariables(templateModel);

            // Process Thymeleaf template to generate the HTML
            String htmlContent = templateEngine.process("email-template.html", context);
            helper.setText(htmlContent, true);

            // Send the email
            javaMailSender.send(message);
            return "Sent successfully!";
        } catch (Exception e) {
            return "Error while sending mail!!!";
        }
    }

    public Map<String,Object> createTransferEmail()  {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("amount", "10,000.00");
        templateModel.put("toBank", "Aga com bank");
        templateModel.put("accountNumber", "11111");
        templateModel.put("receiverReference", "hBSHJabs");
        templateModel.put("transactionReference", "6231dh");
        templateModel.put("dateTime", "11/09/2024 - 07:43 PM");
        return templateModel;
    }
}
