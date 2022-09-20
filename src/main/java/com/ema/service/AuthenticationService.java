package com.ema.service;

import com.ema.dto.LoginRequestPayLoad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    String login(LoginRequestPayLoad loginRequestPayLoad);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
