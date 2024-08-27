package com.UserDeskReservation.backend.Service.Implementation;

import com.UserDeskReservation.backend.DTO.DepartmentDTO;
import com.UserDeskReservation.backend.Entity.Department;
import com.UserDeskReservation.backend.Exception.ResourceNotFoundException;
import com.UserDeskReservation.backend.Mapper.DepartmentMapper;
import com.UserDeskReservation.backend.Message.Message;
import com.UserDeskReservation.backend.Repository.CompanyRepository;
import com.UserDeskReservation.backend.Repository.DepartmentRepository;
import com.UserDeskReservation.backend.Service.Interface.IDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.INSTANCE.mapToDepartment(departmentDTO);

        if (departmentDTO.getComId() != null) {
            department.setCompany(companyRepository.findById(departmentDTO.getComId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found")));
        } else {
            throw new IllegalArgumentException("Company ID cannot be null");
        }

        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.INSTANCE.mapToDepartmentDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
        return DepartmentMapper.INSTANCE.mapToDepartmentDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper.INSTANCE::mapToDepartmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO updatedDepartmentDTO) {
        Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id:"+departmentId));

        if (updatedDepartmentDTO.getDepName() != null) {
            existingDepartment.setDepName(updatedDepartmentDTO.getDepName());
        }
        if (updatedDepartmentDTO.getComId() != null) {
            existingDepartment.setCompany(companyRepository.findById(updatedDepartmentDTO.getComId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found")));
        }

        Department updatedDepartment = departmentRepository.save(existingDepartment);
        return DepartmentMapper.INSTANCE.mapToDepartmentDTO(updatedDepartment);
    }

    @Override
    public Message deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
        departmentRepository.delete(department);
        return new Message("Departman başarıyla silindi", true);
    }
}