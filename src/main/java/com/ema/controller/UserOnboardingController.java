package com.ema.controller;

import com.ema.dto.LoginRequestPayLoad;
import com.ema.dto.LoginResponseDto;
import com.ema.service.serviceImpl.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserOnboardingController {

    private final LoginServiceImpl loginService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestPayLoad loginRequestPayLoad){
        String token = loginService.login(loginRequestPayLoad);
        return new ResponseEntity<>(new LoginResponseDto(token), HttpStatus.OK);
    }
}
