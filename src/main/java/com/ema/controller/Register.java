package com.ema.controller;

import com.ema.dto.SendMailDto;
import com.ema.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@AllArgsConstructor
public class Register {
    private final UserServiceImpl userService;

    @GetMapping("/")
    public String home() {
        return "Hello home";
    }

    @PostMapping("/send-mail")
    public String sendMail(@RequestBody SendMailDto sendMailDto) {
        return userService.sendMail(sendMailDto);
    }

    @PostMapping("/send-with-att")
    public String mailWithAttachments(@RequestBody SendMailDto sendMailDto) throws MessagingException {
        return userService.mailWithAttachments(sendMailDto);
    }
}
