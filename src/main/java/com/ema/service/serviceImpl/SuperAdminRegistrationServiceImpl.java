package com.ema.service.serviceImpl;

import com.ema.dto.CreateUserDto;
import com.ema.dto.MailRequest;
import com.ema.entity.ConfirmationToken;
import com.ema.entity.TemporaryUser;
import com.ema.entity.User;
import com.ema.enums.Role;
import com.ema.enums.UserStatus;
import com.ema.exception.*;
import com.ema.repository.ConfirmationTokenRepository;
import com.ema.repository.TemporaryUserRepository;
import com.ema.repository.UserRepository;
import com.ema.service.SuperAdminRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SuperAdminRegistrationServiceImpl implements SuperAdminRegistrationService {

    private static final String EMAIL_VERIFICATION_LINK = "http://localhost:8080/api/v1/registration/confirm?token=";
    private final TemporaryUserRepository temporaryUserRepository;
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public String createSuperAdmin(CreateUserDto createUserDto) {
        Role role = temporaryUserRepository.findResourceById(Role.SUPER_ADMIN);
        TemporaryUser dbUser = temporaryUserRepository.findByEmail(createUserDto.getEmail());

        if (role != null && dbUser != null && dbUser.isEmailVerified() && dbUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new SuperAdminAlreadyExists("A Super Admin already exists");
        }
        if (!createUserDto.getPassword().equals(createUserDto.getConfirmPassword())) {
            throw new PasswordsDoNotMatch("Passwords do not  match");
        }
        TemporaryUser temporaryUser = TemporaryUser.builder()
                .imageUrl(createUserDto.getImageUrl())
                .employeeId("EMA" + createUserDto.getEmployeeId())
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .isEmailVerified(false)
                .userStatus(UserStatus.INACTIVE)
                .address(createUserDto.getAddress())
                .role(Role.SUPER_ADMIN)
                .phoneNumber(createUserDto.getPhoneNumber())
                .password(createUserDto.getPassword())
                .dateOfBirth(createUserDto.getDateOfBirth())
                .build();
        temporaryUserRepository.save(temporaryUser);

        String token = UUID.randomUUID().toString();
        saveToken(token, temporaryUser);

        String link = EMAIL_VERIFICATION_LINK + token;
        sendMailVerificationLink(createUserDto.getFirstName(), createUserDto.getEmail(), link);
        return "Activate yor account by clicking on the link sent to your email";
    }

    @Override
    public void saveToken(String token, TemporaryUser temporaryUser) {
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .temporaryUser(temporaryUser)
                .token(token)
                .build();
        confirmationTokenRepository.save(confirmationToken);
    }

    public void sendMailVerificationLink(String name, String email, String link) {
        String subject = "Email Verification";
        String body = "Click the link below to verify your email \n" + link;
        MailRequest mailRequest = MailRequest.builder()
                .body(body)
                .name(name)
                .subject(subject)
                .to(email)
                .build();
        mailService.sendMail(mailRequest);
    }

    @Override
    public void resendVerificationEmail(TemporaryUser temporaryUser) {
        String token = UUID.randomUUID().toString();
        String link = EMAIL_VERIFICATION_LINK + token;
        sendMailVerificationLink(temporaryUser.getFirstName(), temporaryUser.getEmail(), link);
        saveToken(token, temporaryUser);

    }

    @Override
    public String confirmToken(TemporaryUser temporaryUser) {
        ConfirmationToken confirmationToken = confirmationTokenRepository
                .findConfirmationTokenByTemporaryUserId(temporaryUser.getId())
                .orElseThrow(() -> new TokenNotFoundException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailAlreadyConfirmed("Email already confirmed.");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            confirmationTokenRepository.delete(confirmationToken);
            resendVerificationEmail(temporaryUser);
            return "Verification link has expired. A new link has been sent to your email";
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);
        enableUser(confirmationToken.getTemporaryUser().getEmail());

        User user = User.builder()
                .imageUrl(temporaryUser.getImageUrl())
                .employeeId(temporaryUser.getEmployeeId())
                .firstName(temporaryUser.getFirstName())
                .lastName(temporaryUser.getLastName())
                .email(temporaryUser.getEmail())
                .isEmailVerified(true)
                .userStatus(UserStatus.ACTIVE)
                .address(temporaryUser.getAddress())
                .role(temporaryUser.getRole())
                .phoneNumber(temporaryUser.getPhoneNumber())
                .password(temporaryUser.getPassword())
                .dateOfBirth(temporaryUser.getDateOfBirth())
                .build();
        userRepository.save(user);

        temporaryUserRepository.delete(temporaryUser);
        return "Email confirmed successfully";

    }

    @Override
    public void enableUser(String email) {
        TemporaryUser temporaryUser = temporaryUserRepository.findByEmail(email);
        if (temporaryUser == null) {
            throw new UserNotFound("User not found");
        }else {
            temporaryUser.setEmailVerified(true);
            temporaryUser.setUserStatus(UserStatus.ACTIVE);
            temporaryUserRepository.save(temporaryUser);
        }
    }
}

