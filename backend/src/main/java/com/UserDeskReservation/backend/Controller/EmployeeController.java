package com.UserDeskReservation.backend.Controller;

import com.UserDeskReservation.backend.DTO.EmployeeDTO;
import com.UserDeskReservation.backend.DTO.LoginDTO;
import com.UserDeskReservation.backend.DTO.PasswordDTO;
import com.UserDeskReservation.backend.Entity.Employee;
import com.UserDeskReservation.backend.Message.Message;
import com.UserDeskReservation.backend.Service.Implementation.EmailService;
import com.UserDeskReservation.backend.Service.Implementation.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<EmployeeDTO> createUser(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdUser = employeeService.createUser(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<EmployeeDTO> getUserById(@PathVariable Long userId) {
        EmployeeDTO employeeDTO = employeeService.getUserById(userId);
        return ResponseEntity.ok(employeeDTO);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllUsers() {
        List<EmployeeDTO> users = employeeService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<EmployeeDTO> updateUser(@PathVariable Long userId, @RequestBody EmployeeDTO updatedUser) {
        EmployeeDTO userDTO = employeeService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        employeeService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO logindto){
        Message loginMessage = employeeService.login(logindto);
        if (loginMessage.getStatus()) {
            return ResponseEntity.ok(loginMessage);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMessage);
        }
    }

    @PostMapping("/changePassword/{employeeId}")
    public ResponseEntity<Message> changePassword(@PathVariable Long employeeId, @RequestBody PasswordDTO passwordDTO) {
        Message message = employeeService.changePassword(employeeId, passwordDTO);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        EmployeeDTO employee = employeeService.findByEmail(email);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-posta bulunamadı");
        }

        String newPassword = generateRandomPassword();
        String encryptedPassword = employeeService.encodePassword(newPassword);

        employeeService.updatePassword(employee.getEmpId(), encryptedPassword);
        emailService.sendEmail(email, "Yeni Şifre", "Yeni şifreniz: " + newPassword);

        return ResponseEntity.ok("Yeni şifre e-posta adresinize gönderildi.");
    }

    private String generateRandomPassword() {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+[]{}|;:,.<>?";
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            password.append(symbols.charAt(random.nextInt(symbols.length())));
        }
        return password.toString();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam("query") String query) {
        return ResponseEntity.ok(employeeService.searchEmployees(query));
    }
}