package com.ema.controller;


import com.ema.dto.CreateUserDto;
import com.ema.dto.FeedDTO;
import com.ema.dto.ModifyUserDto;
import com.ema.entity.Feed;
import com.ema.entity.TimeLog;
import com.ema.entity.User;
import com.ema.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
   private final AdminService adminService;

   @PostMapping("/create-employee")
  public ResponseEntity<String> createEmployee(@RequestBody CreateUserDto createUserDto) throws MessagingException {
     return ResponseEntity.ok(adminService.createEmployee(createUserDto));
  }

  @GetMapping("/allEmployee")
  public ResponseEntity<List<User>> fetchAllEmployee(){
      return ResponseEntity.ok(adminService.viewAllEmployee());
  }

  @GetMapping("/employee/{employeeId}")
  public ResponseEntity<User> getEmployee(@PathVariable Long employeeId){
       return ResponseEntity.ok(adminService.viewEmployeeById(employeeId));
  }

  @GetMapping("/employee/name/{employeeName}")
  public ResponseEntity<List<User>> getEmployeeByName(@PathVariable String employeeName){
       return ResponseEntity.ok(adminService.fetchEmployeeByName(employeeName));
  }

    @GetMapping("/employee/dept/{deptName}")
    public ResponseEntity<List<User>> getEmployeeByDept(@PathVariable String deptName){
        return ResponseEntity.ok(adminService.fetchEmployeeByDepartment(deptName));
    }

    @PutMapping("/edit-employee")
    public ResponseEntity<User> editEmployee(@RequestBody ModifyUserDto modifyUserDto){
        return ResponseEntity.ok(adminService.modifyEmployee(modifyUserDto));
    }


    @PostMapping("/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id)  {
        return ResponseEntity.ok(adminService.deleteEmployee(id));
    }

    @PostMapping("/create-feed")
    public ResponseEntity<String> createFeed(@RequestBody FeedDTO feedDTO)  {
        return ResponseEntity.ok(adminService.makeFeed(feedDTO));
    }

    @PutMapping("/edit-feed/{id}")
    public ResponseEntity<String> editField(@PathVariable Long id, @RequestBody FeedDTO feedDTO)  {
        return ResponseEntity.ok(adminService.editFeed(id,feedDTO));
    }

    @PostMapping("/delete-feef/{id}")
    public ResponseEntity<String> deleteFeed(@PathVariable Long id)  {
        return ResponseEntity.ok(adminService.deleteFeed(id));
    }

    @GetMapping("/all-feed")
    public ResponseEntity<List<Feed>> getAllField(){
        return ResponseEntity.ok(adminService.displayAllFeed());
    }

    @PutMapping("/salary-review/{employeeId}")
    public ResponseEntity<String> salaryEmployee(@PathVariable Long employeeId, @RequestParam BigDecimal newSalary){
       return ResponseEntity.ok(adminService.modifyEmployeeSalary(employeeId,newSalary));
    }

    @GetMapping("/employee-log/{employeeId}")
    public ResponseEntity<TimeLog> employeeLog(@PathVariable Long employeeId){
       return ResponseEntity.ok(adminService.viewEmployeeLog(employeeId));
    }


}
