
package com.company.service;

import com.company.dto.CompanyDTO;
import com.company.dto.CompanyLocationDTO;
import com.company.dto.CompanyRegisterRequestDto;
import com.company.model.Company;
import com.company.model.CompanyLocation;
import com.company.model.VerificationStatus;
import com.company.repository.CompanyRepository;
import com.company.repository.VerificationStatusRepository;
import com.company.rowmapper.CompanyLocationRowmapper;
import com.company.rowmapper.CompanyRowMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

            throw new RuntimeException(
                    "Company already exists");
        }


        // Create Company
        Company company = new Company();

        company.setCompanyName(request.getCompanyName());
        company.setEstablishedYear(request.getEstablishedYear());
        company.setPhone(request.getPhone());
        company.setEmail(request.getEmail());
        company.setWebsiteUrl(request.getWebsiteUrl());
        company.setDescription(request.getDescription());
        company.setCompanySize(request.getCompanySize());


        // Add Locations
        List<CompanyLocation> locations = new ArrayList<>();

        if (request.getLocations() != null) {

            for (CompanyLocationDTO locationDto :
                    request.getLocations()) {

                CompanyLocation location =
                        CompanyLocationRowmapper.toModel(locationDto);

                // Set owning side
                location.setCompany(company);

                locations.add(location);
            }
        }

        company.setLocations(locations);


        // Save Company
        Company savedCompany =
                companyRepository.save(company);


        // Create Verification Status
        VerificationStatus verificationStatus =
                new VerificationStatus();

        verificationStatus.setCompany(savedCompany);

        // Default verification status = false
        verificationStatus.setStatus("UNVERIFIED");

        verificationStatusRepository.save(verificationStatus);


        // Convert to response DTO
        return CompanyRowMapper.toDto(savedCompany);
    }
    
    @Transactional
    public CompanyDTO updateCompany(
            Long companyId,
            CompanyRegisterRequestDto request) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));


        // Update company details
        company.setCompanyName(request.getCompanyName());
        company.setEstablishedYear(request.getEstablishedYear());
        company.setPhone(request.getPhone());
        company.setEmail(request.getEmail());
        company.setWebsiteUrl(request.getWebsiteUrl());
        company.setDescription(request.getDescription());
        company.setCompanySize(request.getCompanySize());


        // Update locations
        company.getLocations().clear();

        if (request.getLocations() != null) {

            for (CompanyLocationDTO locationDto :
                    request.getLocations()) {

                CompanyLocation location =
                        CompanyLocationRowmapper.toModel(locationDto);

                // Establish relationship
                location.setCompany(company);

                company.getLocations().add(location);
            }
        }


        Company updatedCompany =
                companyRepository.save(company);


        return CompanyRowMapper.toDto(updatedCompany);
    }




}
