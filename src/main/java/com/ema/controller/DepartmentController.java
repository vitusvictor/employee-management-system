package com.ema.controller;

import com.ema.dto.CreateDepartmentDto;
import com.ema.dto.ModifyDepartmentDto;
import com.ema.entity.Department;
import com.ema.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;


    @PostMapping("/createDepartment")
    public Department createDepartment(@RequestBody CreateDepartmentDto createDepartmentDto){
        return departmentService.createDepartment(createDepartmentDto);
    }

    @GetMapping("/fetchAllDepartments")
    public List<Department> fetchAllDepartments(){
        return departmentService.fetchAllDepartments();
    }

    @GetMapping("/fetchDepartment/{id}")
    public Department fetchDepartmentById(@PathVariable Long id){
        return departmentService.fetchDepartmentById(id);
    }

    @GetMapping("/fetchDepartment/{name}")
    public List<Department> fetchDepartmentByName(@PathVariable String name){
        return departmentService.fetchDepartmentByName(name);
    }

    @PatchMapping("/modifyDepartment")
    public Department modifyDepartment(@RequestBody ModifyDepartmentDto modifyDepartmentDto){
        return departmentService.modifyDepartment(modifyDepartmentDto);
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id") Long id){
        return departmentService.deleteDepartment(id);
    }
}
