package com.UserDeskReservation.backend.Service.Implementation;

import com.UserDeskReservation.backend.DTO.EmployeeDTO;
import com.UserDeskReservation.backend.DTO.LoginDTO;
import com.UserDeskReservation.backend.DTO.PasswordDTO;
import com.UserDeskReservation.backend.Entity.Employee;
import com.UserDeskReservation.backend.Exception.ResourceNotFoundException;
import com.UserDeskReservation.backend.Mapper.EmployeeMapper;
import com.UserDeskReservation.backend.Message.Message;
import com.UserDeskReservation.backend.Repository.CompanyRepository;
import com.UserDeskReservation.backend.Repository.DepartmentRepository;
import com.UserDeskReservation.backend.Repository.EmployeeRepository;
import com.UserDeskReservation.backend.Service.Interface.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDTO createUser(EmployeeDTO userDTO) {

        Employee employee = EmployeeMapper.INSTANCE.mapToEmployee(userDTO);


        if (userDTO.getDepId() != null) {
            employee.setDepartment(departmentRepository.findById(userDTO.getDepId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found")));
        } else {
            throw new IllegalArgumentException("Department ID cannot be null");
        }

        if (userDTO.getComId() != null) {
            employee.setCompany(companyRepository.findById(userDTO.getComId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found")));
        } else {
            throw new IllegalArgumentException("Company ID cannot be null");
        }

        String initialPassword= employee.getFirstName()+employee.getLastName();

        String encryptedPassword = passwordEncoder.encode(initialPassword);
        employee.setPassword(encryptedPassword);

        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getUserById(Long userId) {
        Employee user = employeeRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return EmployeeMapper.INSTANCE.mapToEmployeeDTO(user);
    }

    @Override
    public List<EmployeeDTO> getAllUsers() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper.INSTANCE::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateUser(Long userId, EmployeeDTO updatedUserDTO) {
        Employee existingEmployee = employeeRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (updatedUserDTO.getFirstName() != null) {
            existingEmployee.setFirstName(updatedUserDTO.getFirstName());
        }
        if (updatedUserDTO.getLastName() != null) {
            existingEmployee.setLastName(updatedUserDTO.getLastName());
        }
        if (updatedUserDTO.getEmail() != null) {
            existingEmployee.setEmail(updatedUserDTO.getEmail());
        }
        if (updatedUserDTO.getDepId() != null) {
            existingEmployee.setDepartment(departmentRepository.findById(updatedUserDTO.getDepId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found")));
        }
        if (updatedUserDTO.getComId() != null) {
            existingEmployee.setCompany(companyRepository.findById(updatedUserDTO.getComId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found")));
        }
        if (updatedUserDTO.getUserRole() != null) {
            existingEmployee.setRole(EmployeeMapper.INSTANCE.stringToRole(updatedUserDTO.getUserRole()));
        }

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return EmployeeMapper.INSTANCE.mapToEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteUser(Long userId) {
        Employee user = employeeRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        employeeRepository.delete(user);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
    @Override
    public Message login(LoginDTO loginDTO) {
        Employee user = employeeRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            if (passwordEncoder.matches(password, encodedPassword)) {
                return new Message("Login Success", true);
            } else {
                return new Message("Login Failed: Incorrect password", false);
            }
        } else {
            return new Message("Email not found", false);
        }
    }
    @Override
    public Message changePassword(Long userId, PasswordDTO passwordDTO) {
        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + userId));

        String encryptedPassword = passwordEncoder.encode(passwordDTO.getNewPassword());
        employee.setPassword(encryptedPassword);
        employeeRepository.save(employee);

        return new Message("Şifreniz başarıyla değiştirildi", true);
    }

    @Override
    public EmployeeDTO findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        return EmployeeMapper.INSTANCE.mapToEmployeeDTO(employee);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void updatePassword(Long userId, String encryptedPassword) {
        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + userId));
        employee.setPassword(encryptedPassword);
        employeeRepository.save(employee);

    }

    @Override
    public List<Employee> searchEmployees(String query) {
        List<Employee> employees = employeeRepository.searchEmployees(query);
        return employees;
    }
}