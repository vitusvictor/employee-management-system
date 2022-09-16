package com.ema.service.serviceImpl;

import com.ema.dto.CreateDepartmentDto;
import com.ema.dto.ModifyDepartmentDto;
import com.ema.entity.Department;
import com.ema.exception.DepartmentAlreadyExists;
import com.ema.exception.DepartmentNotFound;
import com.ema.exception.ListIsEmpty;
import com.ema.repository.DepartmentRepository;
import com.ema.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(CreateDepartmentDto createDepartmentDto) {
        // get context holder to get the role of the user
        // if the role is not super admin throw an exception

        Department dbDepartment = departmentRepository.findDepartmentByName(createDepartmentDto.getName());
        if (dbDepartment != null){
            throw new DepartmentAlreadyExists("Department already exists");
        }
        Department newDepartment = Department.builder()
                .name(createDepartmentDto.getName())
                .build();
        departmentRepository.save(newDepartment);
        return newDepartment;
    }

    @Override
    public List<Department> fetchAllDepartments() {
        // get context holder to get the role of the user
        // if the role is not super admin or admin throw an exception
        List<Department> departmentList = departmentRepository.findAll();
        if (departmentList.isEmpty()){
            throw new ListIsEmpty("No department found");
        }
        return departmentList;
    }

    @Override
    public Department fetchDepartmentById(Long id) {
        // get context holder to get the role of the user
        // if the role is not super admin or admin throw an exception
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFound("Department does not exist"));
    }

    @Override
    public List<Department> fetchDepartmentByName(String name) {
        // get context holder to get the role of the user
        // if the role is not super admin or admin throw an exception
        List<Department> departments = departmentRepository.findDepartmentByNameContainingIgnoreCase(name);
        if (departments == null){
            throw new DepartmentNotFound("Department by that name does not exist");
        }
        return departments;
    }

    @Override
    public Department modifyDepartment(ModifyDepartmentDto modifyDepartmentDto) {
        // get context holder to get the role of the user
        // if the role is not super admin or admin throw an exception
        Department department = departmentRepository.findDepartmentByName(modifyDepartmentDto.getName());
        if (department == null){
            throw new DepartmentNotFound("Department does not exist");
        }
        if (modifyDepartmentDto.getName() != null){
            department.setName(modifyDepartmentDto.getName());
        }
        departmentRepository.save(department);
        return department;
    }

    @Override
    public String deleteDepartment(Long id) {
        // get context holder to get the role of the user
        // if the role is not super admin or admin throw an exception
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFound("Department does not exist"));
        departmentRepository.delete(department);
        return "Department deleted successfully";
    }

}

