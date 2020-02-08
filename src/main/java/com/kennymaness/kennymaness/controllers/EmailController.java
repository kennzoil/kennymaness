package com.kennymaness.kennymaness.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
public class EmailController {
    @RequestMapping(value = "/sendemail")
    public String sendEmail() throws AddressException, MessagingException, IOException {
        sendmail();
        return "Email sent successfully";
    }

    private void sendmail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lewisfromsaintlouis@gmail.com", "DreamWaves3P");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("lewisfromsaintlouis@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("lewisfromsaintlouis@gmail.com"));
        msg.setSubject("This email was sent by my web app");
        msg.setContent("Congratulations! You have received an email that was generated by my web app. Let Kenny know you're proud of him.", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("This is an automatically generated email. Do not reply.", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("static/images/8bit-icon.jpg");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
