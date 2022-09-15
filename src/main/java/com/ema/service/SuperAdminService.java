package com.ema.service;

import com.ema.dto.CreateUserDto;
import com.ema.dto.ModifyUserDto;
import com.ema.entity.User;

import javax.mail.MessagingException;
import java.util.List;

public interface SuperAdminService {
    

    String createAdmin(CreateUserDto createUserDto) throws MessagingException;

    List<User> fetchAllAdmins();

    User fetchAdminById(Long id);

    List<User> fetchAdminByName(String name);

    User modifyAdmin(ModifyUserDto modifyUserDto);

    String deleteAdmin(Long id);


}
