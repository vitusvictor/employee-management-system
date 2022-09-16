package com.ema.repository;

import com.ema.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByName(String name);

    List<Department> findDepartmentByNameContainingIgnoreCase(String name);
}

