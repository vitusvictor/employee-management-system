package com.ema.service.serviceImpl;

import com.ema.dto.CreateUserDto;
import com.ema.dto.SendMailDto;
import com.ema.entity.ConfirmationToken;
import com.ema.entity.User;
import com.ema.enums.Role;
import com.ema.enums.UserStatus;
import com.ema.exception.*;
import com.ema.repository.ConfirmationTokenRepository;
import com.ema.repository.UserRepository;
import com.ema.service.MailService;
import com.ema.service.SuperAdminRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SuperAdminRegistrationServiceImpl implements SuperAdminRegistrationService {

    private static final String EMAIL_VERIFICATION_LINK = "http://localhost:8080/api/v1/registration/confirm?token=";
    private static final String USER_NOT_FOUND = "User not found";
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final MailService mailService;

    private final BCryptPasswordEncoder encoder;

    @Override
    public String createSuperAdmin(CreateUserDto createUserDto) throws MessagingException {
//        Role role = temporaryUserRepository.findResourceById(Role.SUPER_ADMIN);
        User dbUser = userRepository.findByEmail(createUserDto.getEmail());

        if (dbUser != null && dbUser.isEmailVerified() && dbUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new SuperAdminAlreadyExists("A Super Admin already exists");
        }
        if (!createUserDto.getPassword().equals(createUserDto.getConfirmPassword())) {
            throw new PasswordsDoNotMatch("Passwords do not  match");
        }

        User user = User.builder()
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
                .password(encoder.encode(createUserDto.getPassword()))
                .dateOfBirth(createUserDto.getDateOfBirth())
                .build();
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        saveToken(token, user);

        String link = EMAIL_VERIFICATION_LINK + token;
        sendMailVerificationLink(createUserDto.getFirstName(), createUserDto.getEmail(), link);
        return "Activate yor account by clicking on the link sent to your email";
    }

    @Override
    public void saveToken(String token, User user) {
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .token(token)
                .build();
        confirmationTokenRepository.save(confirmationToken);
    }

    public void sendMailVerificationLink(String name, String email, String link) throws MessagingException {
        String sendersName = "EMA Team";
        String subject = "Verify your email";
        String body = "Click the link below to verify your email address and activate your account to start using EMA. ";


        SendMailDto sendMailDto = SendMailDto.builder()
                .receiverEmail(email)
                .subject(subject)
                .senderName(sendersName)
                .receiverName(name)
                .body(body)
                .salutation("Hi")
                .signOff("Regards,")
                .link(link)
                .build();
        mailService.mailWithAttachments(sendMailDto);
    }

    @Override
    public void resendVerificationEmail(User user) throws MessagingException {
        String token = UUID.randomUUID().toString();
        String link = EMAIL_VERIFICATION_LINK + token;
        sendMailVerificationLink(user.getFirstName(), user.getEmail(), link);
        saveToken(token, user);

    }

    @Override
    public String confirmToken(String token) throws MessagingException {

        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
        User user = userRepository.findByEmail(confirmationToken.getUser().getEmail());
        if (confirmationToken == null) {
            throw new TokenNotFound("Token not found");
        }

        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailAlreadyConfirmed("Email already confirmed.");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {

            if (user == null) {
                throw new UserNotFound(USER_NOT_FOUND);
            }

            confirmationTokenRepository.delete(confirmationToken);

            resendVerificationEmail(user);
            return "Verification link has expired. A new link has been sent to your email";
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());

        confirmationTokenRepository.save(confirmationToken);

        enableUser(confirmationToken.getUser().getEmail());
        return "Email confirmed successfully";

    }

    @Override
    public void enableUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFound(USER_NOT_FOUND);
        }else {
            user.setEmailVerified(true);
            user.setUserStatus(UserStatus.ACTIVE);
            userRepository.save(user);
        }
    }
}

