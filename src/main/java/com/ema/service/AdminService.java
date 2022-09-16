package com.ema.service;

import com.ema.dto.CreateUserDto;
import com.ema.dto.FeedDTO;
import com.ema.dto.ModifyUserDto;
import com.ema.entity.Feed;
import com.ema.entity.Salary;
import com.ema.entity.TimeLog;
import com.ema.entity.User;
import lombok.Data;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.List;


public interface AdminService {

    String createEmployee(CreateUserDto createUserDto) throws MessagingException;

    List<User> viewAllEmployee();

    User viewEmployeeById(Long id);

    List<User> fetchEmployeeByName(String name);

    List<User> fetchEmployeeByDepartment(String department);

    User modifyEmployee(ModifyUserDto modifyUserDto);

    String deleteEmployee(Long id);

    String makeFeed(FeedDTO feed);

    String editFeed(Long id, FeedDTO feed1);

    String deleteFeed(Long id);

    List<Feed> displayAllFeed();

    String modifyEmployeeSalary(Long employeeId, BigDecimal newSalary);

    TimeLog viewEmployeeLog(Long employeeId);


}
