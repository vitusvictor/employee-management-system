package com.ema.service.serviceImpl;

import com.ema.dto.CreateUserDto;
import com.ema.dto.ModifyUserDto;
import com.ema.entity.User;
import com.ema.enums.Role;
import com.ema.enums.UserStatus;
import com.ema.exception.ListIsEmpty;
import com.ema.exception.UserAlreadyExists;
import com.ema.exception.UserNotFound;
import com.ema.repository.UserRepository;
import com.ema.service.SuperAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SuperAdminServiceImpl implements SuperAdminService {

    private final SuperAdminRegistrationServiceImpl superAdminRegistrationServiceImpl;

    private static final String EMAIL_VERIFICATION_LINK = "http://localhost:8080/api/v1/registration/confirm?token=";
    private final UserRepository userRepository;

    @Override
    public String createAdmin(CreateUserDto createUserDto) throws MessagingException {

        //get the role of the user
        //code in the context holder to get the role of the user


//        if(!contextHolder.getRole().equals(Role.SUPER_ADMIN)){
//            throw new UnauthorizedOperation("You are not authorized to perform this operation");
//        }
        User dbTempUser = userRepository.findByEmail(createUserDto.getEmail());

        if (dbTempUser != null) {
            throw new UserAlreadyExists("User with email " + createUserDto.getEmail() + " already exists");
        }
        User user = User.builder()
                .imageUrl(createUserDto.getImageUrl())
                .employeeId(createUserDto.getEmployeeId())
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .isEmailVerified(false)
                .userStatus(UserStatus.INACTIVE)
                .address(createUserDto.getAddress())
                .role(Role.ADMIN)
                .phoneNumber(createUserDto.getPhoneNumber())
                .password(createUserDto.getPassword())
                .dateOfBirth(createUserDto.getDateOfBirth())
                .build();
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        superAdminRegistrationServiceImpl.saveToken(token, user);

        String link = EMAIL_VERIFICATION_LINK + token;
        superAdminRegistrationServiceImpl.sendMailVerificationLink(createUserDto.getFirstName(), createUserDto.getEmail(), link);
        return "Activate yor account by clicking on the link sent to your email";
    }

    @Override
    public List<User> fetchAllAdmins(){
        // get context holder to get the role of the user
        // if the role is not super admin throw an exception

        List<User> admins = userRepository.findAllByRole(Role.ADMIN);

        if (admins.isEmpty()){
            throw new ListIsEmpty("No admin found");
        }
        return admins;
    }

    @Override
    public User fetchAdminById(Long id) {
        // get context holder to get the role of the user
        // if the role is not super admin throw an exception

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User does not exist"));
    }

    @Override
    public List<User> fetchAdminByName(String name) {
        // get context holder to get the role of the user
        // if the role is not super admin throw an exception
        List<User> admin = userRepository.findUserByFirstNameOrLastNameContainingIgnoreCase(name, name);
        if (admin == null){
            throw new UserNotFound("User does not exist");
        }
        return admin;
    }

    @Override
    public User modifyAdmin(ModifyUserDto modifyUserDto) {
        // get context holder to get the role of the user
        // if the role is not super admin throw an exception

        User admin = userRepository.findByEmail(modifyUserDto.getEmail());
        if (admin == null) {
                throw new UserNotFound("Admin does not exist");
        }
        if(modifyUserDto.getFirstName() != null){
            admin.setFirstName(modifyUserDto.getFirstName());
        }
        if(modifyUserDto.getLastName() != null){
            admin.setLastName(modifyUserDto.getLastName());
        }
        if(modifyUserDto.getPhoneNumber() != null){
            admin.setPhoneNumber(modifyUserDto.getPhoneNumber());
        }
        if(modifyUserDto.getAddress() != null){
            admin.setAddress(modifyUserDto.getAddress());
        }
        if(modifyUserDto.getEmployeeId() != null){
            admin.setEmployeeId(modifyUserDto.getEmployeeId());
        }
        if(modifyUserDto.getImageUrl() != null){
            admin.setImageUrl(modifyUserDto.getImageUrl());
        }
        userRepository.save(admin);

        return admin;
    }

    @Override
    public String deleteAdmin(Long id) {
        // get context holder to get the role of the user
        // if the role is not super admin throw an exception

        User admin = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("Admin does not exist"));
        userRepository.delete(admin);
        return "Admin deleted successfully";
    }




}

