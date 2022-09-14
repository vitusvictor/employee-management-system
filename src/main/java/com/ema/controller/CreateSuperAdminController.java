package com.ema.controller;

import com.ema.dto.CreateUserDto;
import com.ema.service.SuperAdminRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class CreateSuperAdminController {

    private final SuperAdminRegistrationService superAdminRegistrationService;

    public String createSuperAdmin(CreateUserDto createUserDto) {
        superAdminRegistrationService.createSuperAdmin(createUserDto);
        return "createSuperAdmin";
    }


}
