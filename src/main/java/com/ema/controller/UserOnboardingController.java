package com.ema.controller;

import com.ema.dto.LoginRequestPayLoad;
import com.ema.dto.LoginResponseDto;
import com.ema.service.serviceImpl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserOnboardingController {

    private final AuthenticationServiceImpl authenticate;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestPayLoad loginRequestPayLoad){
        String token = authenticate.login(loginRequestPayLoad);
        return new ResponseEntity<>(new LoginResponseDto(token), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public void logout (HttpServletRequest request, HttpServletResponse response){
        authenticate.logout(request, response);
    }
}
