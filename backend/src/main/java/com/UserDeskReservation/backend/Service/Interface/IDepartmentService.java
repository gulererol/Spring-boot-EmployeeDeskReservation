package com.UserDeskReservation.backend.Service.Interface;

import com.UserDeskReservation.backend.DTO.DepartmentDTO;
import com.UserDeskReservation.backend.Message.Message;

import java.util.List;

public interface IDepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO getDepartmentById(Long departmentId);
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO updatedDepartmentDTO);
    Message deleteDepartment(Long departmentId);
}
