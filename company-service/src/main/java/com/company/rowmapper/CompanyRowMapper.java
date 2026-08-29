package com.company.rowmapper;

import com.company.dto.CompanyDTO;
import com.company.dto.CompanyRegisterRequestDto;
import com.company.model.Company;


public class CompanyRowMapper {

    public static Company toModel(CompanyRegisterRequestDto dto) {

        if (dto == null) {
            return null;
        }

        Company company = new Company();

        company.setCompanyName(dto.getCompanyName());
        company.setEstablishedYear(dto.getEstablishedYear());
        company.setPhone(dto.getPhone());
        company.setEmail(dto.getEmail());
        company.setWebsiteUrl(dto.getWebsiteUrl());
        company.setDescription(dto.getDescription());
        company.setCompanySize(dto.getCompanySize());

        // Locations
        if (dto.getLocations() != null) {

            company.setLocations(
                    dto.getLocations()
                            .stream()
                            .map(CompanyLocationRowmapper::toModel)
                            .peek(location -> location.setCompany(company))
                            .toList()
            );
        }

        return company;
    }


    public static Company toModel(CompanyDTO dto) {

        if (dto == null) {
            return null;
        }

        Company company = new Company();

        company.setCompanyId(dto.getCompanyId());
        company.setCompanyName(dto.getCompanyName());
        company.setEstablishedYear(dto.getEstablishedYear());
        company.setPhone(dto.getPhone());
        company.setEmail(dto.getEmail());
        company.setWebsiteUrl(dto.getWebsiteUrl());
        company.setDescription(dto.getDescription());
        company.setCompanySize(dto.getCompanySize());
        company.setCreatedBy(dto.getCreatedBy());
        company.setCreatedAt(dto.getCreatedAt());
        company.setUpdatedAt(dto.getUpdatedAt());

        // Locations
        if (dto.getLocations() != null) {

            company.setLocations(
                    dto.getLocations()
                            .stream()
                            .map(CompanyLocationRowmapper::toModel)
                            .peek(location -> location.setCompany(company))
                            .toList()
            );
        }

        // Verification Status
        if (dto.getVerificationStatus() != null) {

            company.setVerificationStatus(
                    VerificationStatusRowmapper.toModel(
                            dto.getVerificationStatus()
                    )
            );
        }

        return company;
    }


    public static CompanyDTO toDto(Company company) {

        if (company == null) {
            return null;
        }

        CompanyDTO dto = new CompanyDTO();

        dto.setCompanyId(company.getCompanyId());
        dto.setCompanyName(company.getCompanyName());
        dto.setEstablishedYear(company.getEstablishedYear());
        dto.setPhone(company.getPhone());
        dto.setEmail(company.getEmail());
        dto.setWebsiteUrl(company.getWebsiteUrl());
        dto.setDescription(company.getDescription());
        dto.setCompanySize(company.getCompanySize());
        dto.setCreatedBy(company.getCreatedBy());
        dto.setCreatedAt(company.getCreatedAt());
        dto.setUpdatedAt(company.getUpdatedAt());

        // Locations
        if (company.getLocations() != null) {

            dto.setLocations(
                    company.getLocations()
                            .stream()
                            .map(CompanyLocationRowmapper::toDto)
                            .toList()
            );
        }

        // Verification Status
        if (company.getVerificationStatus() != null) {

            dto.setVerificationStatus(
                    VerificationStatusRowmapper.toDto(
                            company.getVerificationStatus()
                    )
            );
        }

        return dto;
    }
}
