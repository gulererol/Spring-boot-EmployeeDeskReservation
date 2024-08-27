package com.UserDeskReservation.backend.Mapper;

import com.UserDeskReservation.backend.DTO.CompanyDTO;
import com.UserDeskReservation.backend.Entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO mapToCompanyDTO(Company company);

    Company mapToCompany(CompanyDTO companyDTO);
}
