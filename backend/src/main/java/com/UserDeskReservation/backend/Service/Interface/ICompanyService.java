package com.UserDeskReservation.backend.Service.Interface;

import com.UserDeskReservation.backend.DTO.CompanyDTO;

import java.util.List;

public interface ICompanyService {
    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO getCompanyById(Long companyId);
    List<CompanyDTO> getAllCompanies();
    CompanyDTO updateCompany(Long companyId, CompanyDTO updatedCompanyDTO);
    void deleteCompany(Long companyId);
}
