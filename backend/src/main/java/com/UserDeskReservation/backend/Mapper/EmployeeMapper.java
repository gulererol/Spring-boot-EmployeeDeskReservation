package com.UserDeskReservation.backend.Mapper;

import com.UserDeskReservation.backend.DTO.EmployeeDTO;
import com.UserDeskReservation.backend.Entity.Employee;
import com.UserDeskReservation.backend.enums.RoleEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "department.depId", target = "depId")
    @Mapping(source = "company.comId", target = "comId")
    @Mapping(source = "role", target = "userRole", qualifiedByName = "roleToString")
    EmployeeDTO mapToEmployeeDTO(Employee employee);

    @Mapping(source = "depId", target = "department.depId")
    @Mapping(source = "comId", target = "company.comId")
    @Mapping(source = "userRole", target = "role", qualifiedByName = "stringToRole")
    Employee mapToEmployee(EmployeeDTO employeeDTO);

    @Named("roleToString")
    default String roleToString(RoleEnum roleEnum) {
        return roleEnum != null ? roleEnum.name() : null;
    }

    @Named("stringToRole")
    default RoleEnum stringToRole(String role) {
        return role != null ? RoleEnum.valueOf(role) : null;
    }
}