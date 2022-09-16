package com.ema.service;

import com.ema.dto.CreateUserDto;
import com.ema.entity.User;

import javax.mail.MessagingException;

public interface SuperAdminRegistrationService {
    String createSuperAdmin(CreateUserDto createUserDto) throws MessagingException;

    void saveToken(String token, User user);

    void resendVerificationEmail(User user) throws MessagingException;

    String confirmToken(String token) throws MessagingException;

    void enableUser(String email);
}
