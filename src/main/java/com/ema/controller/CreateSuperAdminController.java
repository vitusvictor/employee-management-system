package com.ema.controller;

import com.ema.dto.CreateUserDto;
import com.ema.service.SuperAdminRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class CreateSuperAdminController {

    private final SuperAdminRegistrationService superAdminRegistrationService;

    @PostMapping("/createSuperAdmin")
    public String createSuperAdmin(@RequestBody @Valid CreateUserDto createUserDto) throws MessagingException {
        return superAdminRegistrationService.createSuperAdmin(createUserDto);
    }



}
