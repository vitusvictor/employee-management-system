package com.ema.service.serviceImpl;

import com.ema.exceptions.FailedMailException;
import com.ema.dto.SendMailDto;
import com.ema.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    @Override
    public String sendMail(SendMailDto sendMailDto) throws MailException{
        SimpleMailMessage simpleMailSender = new SimpleMailMessage();

        simpleMailSender.setTo(sendMailDto.getReceiverEmail());
        simpleMailSender.setSubject(sendMailDto.getSubject());
        simpleMailSender.setText("Hello " + sendMailDto.getReceiverName() +
                "\n\n" + sendMailDto.getBody() +
                "\n\n Regards, \n" +
                sendMailDto.getSenderName());

        try {
            javaMailSender.send(simpleMailSender);
            return "Mail successfully sent";
        } catch (MailException e){
            throw new FailedMailException("Failed to send mail because " + e.getMessage());
        }
    }

    @Override
    public String mailWithAttachments(SendMailDto sendMailDto) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vitusvictor41@gmail.com", "jcxrsncnloqjhmyt");
            }
        });

        Message message = new MimeMessage(session);
        message.setSubject(sendMailDto.getSubject());

        Address addressTo = new InternetAddress(sendMailDto.getReceiverEmail());
        message.setRecipient(Message.RecipientType.TO, addressTo);

        MimeBodyPart messageBodyPart = new MimeBodyPart();


        messageBodyPart.setText(
                "<div>" +
                        sendMailDto.getSalutation() + " " + sendMailDto.getReceiverName() +
                "<br><br>" +
                        sendMailDto.getBody() +
                "<br>" +
                        sendMailDto.getSignoff() +
                "<br>" +
                        sendMailDto.getSenderName() +
                "</div>" +

                "<a href=\"" + sendMailDto.getLink() + "\" target=\"_blank\">" +
                        "<button style=\"" +
                        "background-color: #4CAF50;\n" +
                        "  border: none;\n" +
                        "  color: white;\n" +
                        "  padding: 15px;\n" +
                        "  text-align: center;\n" +
                        "  text-decoration: none;\n" +
                        "  display: inline-block;\n" +
                        "  font-size: 16px;\n" +
                        "  margin: 4px 2px;\n" +
                        "  border-radius: 12px;\n" +
                        "  cursor: pointer;\">" +
                        "Confirm email" +
                        "</button></a>");

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        messageBodyPart.setHeader("Content-Type", "text/html");
        message.saveChanges();

        try {
            Transport.send(message);
            return "Mail sent successfully!";
        } catch (MailException e){
            throw new FailedMailException("Failed to send mail because " + e.getMessage());
        }
    }
}
