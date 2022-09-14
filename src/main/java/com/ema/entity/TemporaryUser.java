package com.ema.entity;

import com.ema.enums.Role;
import com.ema.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "temporary_users_table")
public class TemporaryUser extends BaseClass {
    private String imageUrl;

    @NotNull
    private String employeeId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    @NotNull
    private String address;

    @NotNull
    private boolean isEmailVerified;

    @NotNull
    private UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Size(min = 8, max = 45)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one uppercase, " +
                    "one lowercase letter, one special character and at least 8 or more characters")
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    private Date dateOfBirth;

    @OneToOne
    @JoinColumn(name = "salary_id", referencedColumnName = "id")
    private Salary salary;
}

