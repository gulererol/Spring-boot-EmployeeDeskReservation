package com.UserDeskReservation.backend.Service.Implementation;

import com.UserDeskReservation.backend.DTO.CompanyDTO;
import com.UserDeskReservation.backend.Entity.Company;
import com.UserDeskReservation.backend.Exception.ResourceNotFoundException;
import com.UserDeskReservation.backend.Mapper.CompanyMapper;
import com.UserDeskReservation.backend.Repository.CompanyRepository;
import com.UserDeskReservation.backend.Service.Interface.ICompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = CompanyMapper.INSTANCE.mapToCompany(companyDTO);
        Company savedCompany = companyRepository.save(company);
        return CompanyMapper.INSTANCE.mapToCompanyDTO(savedCompany);
    }

    @Override
    public CompanyDTO getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        return CompanyMapper.INSTANCE.mapToCompanyDTO(company);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(CompanyMapper.INSTANCE::mapToCompanyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO updateCompany(Long companyId, CompanyDTO updatedCompanyDTO) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        company.setComName(updatedCompanyDTO.getComName());
        Company updatedCompany = companyRepository.save(company);
        return CompanyMapper.INSTANCE.mapToCompanyDTO(updatedCompany);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        companyRepository.delete(company);
    }
}