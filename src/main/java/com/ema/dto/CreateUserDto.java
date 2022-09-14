package com.ema.dto;

import com.ema.enums.Role;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateUserDto {

        private String imageUrl;
        private String employeeId;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private Role role;
        private String phoneNumber;
        private String password;
        private String confirmPassword;
        private Date dateOfBirth;
}

