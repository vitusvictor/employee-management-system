package com.ema.dto;

import com.ema.enums.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateUserDto {

        @NotNull
        private String imageUrl;

        @NotNull
        private String employeeId;

        @NotNull
        private String firstName;

        @NotNull
        private String lastName;

        @NotNull
        @Email (message = "Email should be valid")
        private String email;

        @NotNull
        private String address;

        @NotNull
        private String phoneNumber;

        @NotNull (message = "Password should not be null")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
                message = "Password must contain at least one digit, one uppercase, " +
                        "one lowercase letter, one special character and at least 8 or more characters")
        private String password;

        @NotNull
        private String confirmPassword;

        @NotNull
        private Date dateOfBirth;
}

