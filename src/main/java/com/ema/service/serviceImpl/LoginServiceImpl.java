package com.ema.service.serviceImpl;

import com.ema.dto.LoginRequestPayLoad;
import com.ema.entity.User;
import com.ema.enums.UserStatus;
import com.ema.exceptions.UsernameNotFoundException;
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
        User user = userRepository.findByEmail(loginRequestPayLoad.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("user not found!"));

        if(user.getUserStatus() != UserStatus.ACTIVE){
            return "Please verify your email";
        }
        return null;
    }
}
