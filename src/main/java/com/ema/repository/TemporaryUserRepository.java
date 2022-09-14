package com.ema.repository;

import com.ema.entity.TemporaryUser;
import com.ema.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporaryUserRepository extends JpaRepository<TemporaryUser, Long> {
    TemporaryUser findByEmail(String email);

    Role findResourceById(Role superAdmin);
}

