package com.ema.repository;

import com.ema.entity.User;
import com.ema.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Role findResourceById(Role superAdmin);
    User findByEmail(String email);
    List<User> findAllByRole(Role role);

    List<User> findUserByFirstNameOrLastNameContainingIgnoreCase(String firstName, String lastName);

}


