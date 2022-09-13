package com.ema.service.serviceImpl;

import com.ema.dto.LoginRequestPayLoad;
import com.ema.repository.UserRepository;
import com.ema.security.filter.JwtUtils;
import com.ema.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final UserServiceImpl userService;

    private final AuthenticationManager authenticationManager;

    @Override
    public String login(LoginRequestPayLoad loginRequestPayLoad) {
        return null;
    }
}
