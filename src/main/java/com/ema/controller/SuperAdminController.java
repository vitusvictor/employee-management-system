package com.ema.controller;

import com.ema.dto.CreateUserDto;
import com.ema.dto.ModifyUserDto;
import com.ema.entity.User;
import com.ema.service.SuperAdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class SuperAdminController {
    private final SuperAdminService superAdminService;

    @PostMapping("/createAdmin")
    public String createAdmin(@RequestBody CreateUserDto createUserDto){
        return superAdminService.createAdmin(createUserDto);
    }

    @GetMapping("/fetchAllAdmins")
    public List<User> getAllAdmins(){
        return superAdminService.fetchAllAdmins();
    }

    @GetMapping("/fetchAdmin/{id}")
    public User fetchAdminById(@PathVariable("id") Long id){
        return superAdminService.fetchAdminById(id);
    }

    @GetMapping("/fetchAdmin/{name}")
    public List<User> fetchAdminByName(@PathVariable("name") String name){
        return superAdminService.fetchAdminByName(name);
    }

    @PatchMapping("/modifyAdmin")
    public User modifyAdmin(@RequestBody ModifyUserDto modifyUserDto){
        return superAdminService.modifyAdmin(modifyUserDto);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable("id") Long id){
        return superAdminService.deleteAdmin(id);
    }
}
