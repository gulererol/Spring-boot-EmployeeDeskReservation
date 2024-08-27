package com.UserDeskReservation.backend.Mapper;

import com.UserDeskReservation.backend.DTO.DepartmentDTO;
import com.UserDeskReservation.backend.Entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(source = "company.comId", target = "comId")
    DepartmentDTO mapToDepartmentDTO(Department department);

    @Mapping(source = "comId", target = "company.comId")
    Department mapToDepartment(DepartmentDTO departmentDTO);
}
