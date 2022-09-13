package com.ema.service.impl;

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
        mailService.sendMail(sendMailDto);
        return "mail sent";
    }

    public String mailWithAttachments(SendMailDto sendMailDto) throws MessagingException {
        return mailService.mailWithAttachments(sendMailDto);
    }
}
