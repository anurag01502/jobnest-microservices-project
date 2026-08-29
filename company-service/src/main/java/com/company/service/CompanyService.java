package com.company.service;

import com.company.dto.CompanyDTO;
import com.company.dto.CompanyRegisterRequestDto;
import com.company.exception.CustomRuntimeException;
import com.company.model.Company;
import com.company.model.VerificationStatus;
import com.company.repository.CompanyRepository;
import com.company.repository.VerificationStatusRepository;
import com.company.rowmapper.CompanyRowMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final VerificationStatusRepository verificationStatusRepository;


    @Transactional
    public CompanyDTO registerCompany(
            CompanyRegisterRequestDto request) {

        // Check if company already exists
        if (companyRepository.existsByCompanyNameIgnoreCase(
                request.getCompanyName())) {

            throw new CustomRuntimeException(
                    "Company already exists",HttpStatus.BAD_REQUEST);
        }


        // Convert DTO to Model
        Company company =
                CompanyRowMapper.toModel(request);


        // Save Company
        Company savedCompany =
                companyRepository.save(company);


        // Create Verification Status
        VerificationStatus verificationStatus =
                new VerificationStatus();

        verificationStatus.setCompany(savedCompany);

        // Default status
        verificationStatus.setStatus("UNVERIFIED");

        verificationStatusRepository.save(
                verificationStatus);


        // Convert Model to DTO
        return CompanyRowMapper.toDto(savedCompany);
    }


    @Transactional
    public CompanyDTO updateCompany(
            Long companyId,
            CompanyRegisterRequestDto request) {

        // Find existing company
        Company company =
                companyRepository.findById(companyId)
                        .orElseThrow(() ->
                                new CustomRuntimeException(
                                        "Company not found",HttpStatus.BAD_REQUEST));


        // Update fields using mapper
        Company updatedData =
                CompanyRowMapper.toModel(request);


        company.setCompanyName(
                updatedData.getCompanyName());

        company.setEstablishedYear(
                updatedData.getEstablishedYear());

        company.setPhone(
                updatedData.getPhone());

        company.setEmail(
                updatedData.getEmail());

        company.setWebsiteUrl(
                updatedData.getWebsiteUrl());

        company.setDescription(
                updatedData.getDescription());

        company.setCompanySize(
                updatedData.getCompanySize());


        // Update locations
        company.getLocations().clear();

        if (updatedData.getLocations() != null) {

            updatedData.getLocations()
                    .forEach(location -> {
                        location.setCompany(company);
                        company.getLocations().add(location);
                    });
        }


        // Save
        Company savedCompany =
                companyRepository.save(company);


        // Return DTO
        return CompanyRowMapper.toDto(savedCompany);
    }
    
 
    @Transactional
    public void deleteCompany(
            Long companyId,
            Long userId) {

        // Find company
        Company company =
                companyRepository.findById(companyId)
                        .orElseThrow(() ->
                                new CustomRuntimeException(
                                        "Company not found",
                                        HttpStatus.NOT_FOUND
                                )
                        );


        // Check ownership
        if (!company.getCreatedBy().equals(userId)) {

            throw new CustomRuntimeException(
                    "You are not authorized to delete this company",
                    HttpStatus.FORBIDDEN
            );
        }


        // Delete company
        companyRepository.delete(company);
    }

}
