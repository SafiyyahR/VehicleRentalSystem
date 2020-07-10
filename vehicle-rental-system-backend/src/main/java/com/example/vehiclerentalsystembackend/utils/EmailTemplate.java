package com.example.vehiclerentalsystembackend.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class EmailTemplate {
    public String sendEmail(String message, String recepEmail, String subject) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vrentalsystem@gmail.com", "VrsAdmin");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("vrentalsystem@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepEmail));
        msg.setSubject(subject);
        msg.setContent(message, "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
        return "Email sent.";
    }
}
