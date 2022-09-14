package com.ema.service;

import com.ema.dto.CreateUserDto;
import com.ema.entity.TemporaryUser;

public interface SuperAdminRegistrationService {
    String createSuperAdmin(CreateUserDto createUserDto);

    void saveToken(String token, TemporaryUser temporaryUser);

    void resendVerificationEmail(TemporaryUser temporaryUser);

    String confirmToken(TemporaryUser temporaryUser);

    void enableUser(String email);
}
