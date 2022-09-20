package com.ema.service.serviceImpl;

import com.ema.dto.LoginRequestPayLoad;
import com.ema.entity.User;
import com.ema.enums.UserStatus;
import com.ema.exception.UserNotFound;
import com.ema.exception.VerifyEmailException;
import com.ema.repository.UserRepository;
import com.ema.security.filter.JwtUtils;
import com.ema.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final UserServiceImpl userService;

    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder;

    private final HttpSession httpSession;

    @Override
    public String login(LoginRequestPayLoad loginRequestPayLoad) {
        User user = userRepository.findByEmail(loginRequestPayLoad.getEmail());

        if (user == null) {
            throw new UserNotFound("user not found!");
        }

        if (!encoder.matches(loginRequestPayLoad.getPassword(), user.getPassword())) {
            throw new UserNotFound("invalid login credentials!");
        }

        if(!user.getEmail().isEmpty() && user.getUserStatus() != UserStatus.ACTIVE){
            throw new VerifyEmailException("Please verify your email!");
        }

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (loginRequestPayLoad.getEmail(), loginRequestPayLoad.getPassword())
            );
        }catch (BadCredentialsException ex){
            throw new UsernameNotFoundException("Invalid Credential");
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginRequestPayLoad.getEmail());

        return jwtUtils.generateToken(userDetails);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
