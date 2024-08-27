package com.UserDeskReservation.backend.Service.Interface;

import com.UserDeskReservation.backend.DTO.EmployeeDTO;
import com.UserDeskReservation.backend.DTO.LoginDTO;
import com.UserDeskReservation.backend.DTO.PasswordDTO;
import com.UserDeskReservation.backend.Entity.Employee;
import com.UserDeskReservation.backend.Message.Message;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IEmployeeService {
    EmployeeDTO createUser(EmployeeDTO userDTO);
    EmployeeDTO getUserById(Long userId);
    List<EmployeeDTO> getAllUsers();
    EmployeeDTO updateUser(Long userId, EmployeeDTO updatedUserDTO);
    void deleteUser(Long userId);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    Message login(LoginDTO loginDTO);
    Message changePassword(Long userId, PasswordDTO passwordDTO);
    EmployeeDTO findByEmail(String email);
    String encodePassword(String password);
    void updatePassword(Long userId, String encryptedPassword);
    List<Employee> searchEmployees(String query);
}