package com.ema.service.serviceImpl;

import com.ema.entity.User;
import com.ema.repository.UserRepository;
import com.ema.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collections;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        if(user == null) {
            log.error("user not found");
            throw new UsernameNotFoundException("User not found!");
        }
        else {
            log.info("user found");

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singleton(authority));
        }

    }
}
