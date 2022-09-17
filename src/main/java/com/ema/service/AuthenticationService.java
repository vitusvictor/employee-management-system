package com.ema.service;

import com.ema.dto.LoginRequestPayLoad;

public interface AuthenticationService {

    String login(LoginRequestPayLoad loginRequestPayLoad);

    String logout();
}
