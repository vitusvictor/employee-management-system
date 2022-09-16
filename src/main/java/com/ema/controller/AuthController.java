package com.ema.controller;

import com.ema.service.SuperAdminRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@AllArgsConstructor
@RequestMapping("/registration")
public class AuthController {
    private final SuperAdminRegistrationService superAdminRegistrationService;

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token) throws MessagingException {
        return superAdminRegistrationService.confirmToken(token);
    }
}
