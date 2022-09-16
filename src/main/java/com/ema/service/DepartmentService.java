package com.ema.service;

import com.ema.dto.CreateDepartmentDto;
import com.ema.dto.ModifyDepartmentDto;
import com.ema.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(CreateDepartmentDto createDepartmentDto);

    List<Department> fetchAllDepartments();

    Department fetchDepartmentById(Long id);

    List<Department> fetchDepartmentByName(String name);

    Department modifyDepartment(ModifyDepartmentDto modifyDepartmentDto);

    String deleteDepartment(Long id);
}
