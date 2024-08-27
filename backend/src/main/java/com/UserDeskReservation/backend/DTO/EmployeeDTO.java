package com.UserDeskReservation.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private Long empId;
    private String firstName;
    private String lastName;
    private String email;
    private Long depId;
    private Long comId;
    private String userRole;
}
