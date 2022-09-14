package com.ema.dto;

import com.ema.enums.Role;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ModifyUserDto {
    private String email;
    private String imageUrl;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
}
