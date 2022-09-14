package com.ema.service;

import com.ema.dto.SendMailDto;
import org.springframework.mail.MailException;

import javax.mail.MessagingException;

public interface MailService {
    String sendMail(SendMailDto sendMailDto) throws MailException;

    String mailWithAttachments(SendMailDto sendMailDto) throws MessagingException;
}
