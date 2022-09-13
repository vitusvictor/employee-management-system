package com.ema.service.serviceImpl;

import com.ema.dto.SendMailDto;
import com.ema.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class UserServiceImpl {
    private final MailService mailService;

    public String sendMail(SendMailDto sendMailDto) {
        return mailService.sendMail(sendMailDto);
    }

    public String mailWithAttachments(SendMailDto sendMailDto) throws MessagingException {
        return mailService.mailWithAttachments(sendMailDto);
    }
}
