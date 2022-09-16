package com.ema.repository;

import com.ema.entity.User;
import com.ema.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.salary.amount=?2 WHERE u.id=?1")
    void changeSalary(Long id, BigDecimal newSalary);

    Role findResourceById(Role superAdmin);
    User findByEmail(String email);
    List<User> findAllByRole(Role role);
    List<User> findUserByFirstNameOrLastNameContainingIgnoreCase(String firstName, String lastName);

    List<User> findUserByDepartmentAndRole(String name, Role role);

    List<User> findUserByFirstNameOrLastNameContainingIgnoreCaseAndRole(String firstName, String lastName, Role role);
}


