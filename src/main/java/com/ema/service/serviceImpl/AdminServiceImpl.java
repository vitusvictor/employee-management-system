package com.ema.service.serviceImpl;

import com.ema.dto.CreateUserDto;
import com.ema.dto.FeedDTO;
import com.ema.dto.ModifyUserDto;
import com.ema.entity.Feed;
import com.ema.entity.Salary;
import com.ema.entity.TimeLog;
import com.ema.entity.User;
import com.ema.enums.Role;
import com.ema.enums.UserStatus;
import com.ema.exception.FeedNotFound;
import com.ema.exception.ListIsEmpty;
import com.ema.exception.UserAlreadyExists;
import com.ema.exception.UserNotFound;
import com.ema.repository.FeedRepository;
import com.ema.repository.TimeLogRepository;
import com.ema.repository.UserRepository;
import com.ema.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    private final FeedRepository feedRepository;

    private final TimeLogRepository timeLogRepository;
    private final SuperAdminRegistrationServiceImpl superAdminRegistrationServiceImpl;
    private static final String EMAIL_VERIFICATION_LINK = "http://localhost:8080/api/v1/registration/confirm?token=";


    @Override
    public String createEmployee(CreateUserDto createUserDto) throws MessagingException {
        //get the role of the user
        //code in the context holder to get the role of the user


//        if(!contextHolder.getRole().equals(Role.ADMIN)){
//            throw new UnauthorizedOperation("You are not authorized to perform this operation");
//        }
        User tempUser = userRepository.findByEmail(createUserDto.getEmail());

        if (tempUser != null) {
            throw new UserAlreadyExists("User with email " + createUserDto.getEmail() + " already exists");
        }
        User user = User.builder()
                .imageUrl(createUserDto.getImageUrl())
                .employeeId(createUserDto.getEmployeeId())
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .isEmailVerified(false)
                .userStatus(UserStatus.INACTIVE)
                .address(createUserDto.getAddress())
                .role(Role.EMPLOYEE)
                .phoneNumber(createUserDto.getPhoneNumber())
                .password(createUserDto.getPassword())
                .dateOfBirth(createUserDto.getDateOfBirth())
                .build();
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        superAdminRegistrationServiceImpl.saveToken(token, user);

        String link = EMAIL_VERIFICATION_LINK + token;
        superAdminRegistrationServiceImpl.sendMailVerificationLink(createUserDto.getFirstName(), createUserDto.getEmail(), link);
        return "Activate yor account by clicking on the link sent to your email";
    }

    @Override
    public List<User> viewAllEmployee() {
        // get context holder to get the role of the user
        // if the role is not super admin throw an exception

        List<User> employees = userRepository.findAllByRole(Role.EMPLOYEE);

        if (employees.isEmpty()){
            throw new ListIsEmpty("No Employee found");
        }
        return employees;

    }

    @Override
    public User viewEmployeeById(Long id) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("Employee does not exist"));
    }

    @Override
    public List<User> fetchEmployeeByName(String name) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception
        List<User> employee = userRepository.findUserByFirstNameOrLastNameContainingIgnoreCaseAndRole(name, name,Role.EMPLOYEE);
        if (employee == null){
            throw new UserNotFound("Employee does not exist");
        }
        return employee;
    }

    @Override
    public List<User> fetchEmployeeByDepartment(String department) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception
        List<User> employee = userRepository.findUserByDepartmentAndRole(department, Role.EMPLOYEE);
        if (employee == null){
            throw new UserNotFound("Employee does not exist");
        }
        return employee;
    }

    @Override
    public User modifyEmployee(ModifyUserDto modifyUserDto) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception

        User employee = userRepository.findByEmail(modifyUserDto.getEmail());
        if (employee == null) {
            throw new UserNotFound("Admin does not exist");
        }
        if(modifyUserDto.getFirstName() != null){
            employee.setFirstName(modifyUserDto.getFirstName());
        }
        if(modifyUserDto.getLastName() != null){
            employee.setLastName(modifyUserDto.getLastName());
        }
        if(modifyUserDto.getPhoneNumber() != null){
            employee.setPhoneNumber(modifyUserDto.getPhoneNumber());
        }
        if(modifyUserDto.getAddress() != null){
            employee.setAddress(modifyUserDto.getAddress());
        }
        if(modifyUserDto.getEmployeeId() != null){
            employee.setEmployeeId(modifyUserDto.getEmployeeId());
        }
        if(modifyUserDto.getImageUrl() != null){
            employee.setImageUrl(modifyUserDto.getImageUrl());
        }
        userRepository.save(employee);

        return employee;

}

    @Override
    public String deleteEmployee(Long id) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception

        User employee = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("Admin does not exist"));
        userRepository.delete(employee);
        return "Admin deleted successfully";
    }

    @Override
    public String makeFeed(FeedDTO feed) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception

        Feed feed1 = Feed.builder()
                .imageUrl(feed.getImageUrl())
                .content(feed.getContent())
                .title(feed.getTitle())
                //admin gotten from context holder
                .user(new User())
                .build();
        feedRepository.save(feed1);

        return "Feed Created Successfully";
    }

    @Override
    public String editFeed(Long id, FeedDTO feed1) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception

        Feed feed = feedRepository.findById(id).orElseThrow(()-> new FeedNotFound("Feed Not Found"));
        feed.setContent(feed1.getContent());
        feed.setImageUrl(feed1.getImageUrl());
        feed.setTitle(feed1.getTitle());
        feedRepository.save(feed);
        return "Feed Edited Successfully";
    }

    @Override
    public String deleteFeed(Long id) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception
        feedRepository.deleteById(id);
        return "Feed Deleted Successfully";
    }

    @Override
    public List<Feed> displayAllFeed() {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception

        List<Feed> feedList = feedRepository.findAll();
        if (feedList == null){
            throw new FeedNotFound("No feed yet");
        }
        return feedList;
    }

    @Override
    public String modifyEmployeeSalary(Long employeeId, BigDecimal newSalary) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception
        userRepository.changeSalary(employeeId, newSalary);

        return "Employee Salary has been reviewed!";
    }

    @Override
    public TimeLog viewEmployeeLog(Long employeeId) {
        // get context holder to get the role of the user
        // if the role is not admin throw an exception

        User employee = userRepository.findById(employeeId).orElseThrow(()-> new UserNotFound("Employee Found"));
        return timeLogRepository.findTimeLogByUser(employee);
    }
}
