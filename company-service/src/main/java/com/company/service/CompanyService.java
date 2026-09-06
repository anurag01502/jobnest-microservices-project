package com.company.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dto.CompanyDTO;
import com.company.dto.CompanyFilterRequestDto;
import com.company.dto.CompanyRegisterRequestDto;
import com.company.dto.UserProfileResponseExternalDto;
import com.company.exception.CustomRuntimeException;
import com.company.model.Company;
import com.company.model.VerificationStatus;
import com.company.repository.CompanyRepository;
import com.company.repository.VerificationStatusRepository;
import com.company.rowmapper.CompanyRowMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private static final Logger logger =
            LoggerFactory.getLogger(CompanyService.class);

    private final CompanyRepository companyRepository;
    private final VerificationStatusRepository verificationStatusRepository;
    private final UserExternalService userExternalService;


    @Transactional
    public CompanyDTO registerCompany(
            CompanyRegisterRequestDto request,
            Authentication authentication) {

        logger.info(
                "Starting company registration for company name: {}",
                request.getCompanyName()
        );

        // Check if company already exists
        if (companyRepository.existsByCompanyNameIgnoreCase(
                request.getCompanyName())) {

            logger.warn(
                    "Company registration failed. Company already exists: {}",
                    request.getCompanyName()
            );

            throw new CustomRuntimeException(
                    "Company already exists",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Get authenticated user
        UserProfileResponseExternalDto userProfile =
                userExternalService.getUser(
                        authentication.getName()
                );

        logger.debug(
                "Authenticated user ID: {} is registering company",
                userProfile.getUserId()
        );

        // Convert request to Company
        Company company =
                CompanyRowMapper.toModel(request);

        // IMPORTANT:
        // Owner is taken from authenticated user,
        // not from client request.
        company.setCreatedBy(
                userProfile.getUserId()
        );

        logger.debug(
                "Company owner set to user ID: {}",
                userProfile.getUserId()
        );

        // Save Company
        Company savedCompany =
                companyRepository.save(company);

        logger.info(
                "Company saved successfully. Company ID: {}",
                savedCompany.getCompanyId()
        );

        // Create Verification Status
        VerificationStatus verificationStatus =
                new VerificationStatus();

        verificationStatus.setCompany(savedCompany);
        verificationStatus.setStatus("UNVERIFIED");

        verificationStatusRepository.save(
                verificationStatus
        );

        logger.info(
                "Verification status created for company ID: {} with status: UNVERIFIED",
                savedCompany.getCompanyId()
        );

        // Convert Model to DTO
        CompanyDTO companyDTO =
                CompanyRowMapper.toDto(savedCompany);

        logger.info(
                "Company registration completed successfully. Company ID: {}",
                savedCompany.getCompanyId()
        );

        return companyDTO;
    }


    @Transactional
    public CompanyDTO updateCompany(
            Long companyId,
            CompanyRegisterRequestDto request,
            Authentication authentication) {

        logger.info(
                "Starting company update. Company ID: {}",
                companyId
        );

        // Find existing company
        Company company =
                companyRepository.findById(companyId)
                        .orElseThrow(() -> {

                            logger.warn(
                                    "Company update failed. Company not found. Company ID: {}",
                                    companyId
                            );

                            return new CustomRuntimeException(
                                    "Company not found",
                                    HttpStatus.NOT_FOUND
                            );
                        });

        // Get authenticated user
        UserProfileResponseExternalDto userProfile =
                userExternalService.getUser(
                        authentication.getName()
                );

        logger.debug(
                "Authenticated user ID: {} attempting to update company ID: {}",
                userProfile.getUserId(),
                companyId
        );

        // Check ownership
        if (!Objects.equals(
                company.getCreatedBy(),
                userProfile.getUserId())) {

            logger.warn(
                    "Unauthorized company update attempt. User ID: {}, Company ID: {}",
                    userProfile.getUserId(),
                    companyId
            );

            throw new CustomRuntimeException(
                    "You are not authorized to update this company",
                    HttpStatus.FORBIDDEN
            );
        }

        // Convert request to model
        Company updatedData =
                CompanyRowMapper.toModel(request);

        // Update basic company details
        company.setCompanyName(
                updatedData.getCompanyName()
        );

        company.setEstablishedYear(
                updatedData.getEstablishedYear()
        );

        company.setPhone(
                updatedData.getPhone()
        );

        company.setEmail(
                updatedData.getEmail()
        );

        company.setWebsiteUrl(
                updatedData.getWebsiteUrl()
        );

        company.setDescription(
                updatedData.getDescription()
        );

        company.setCompanySize(
                updatedData.getCompanySize()
        );

        logger.debug(
                "Basic company details updated. Company ID: {}",
                companyId
        );

        // Update locations
        company.getLocations().clear();

        if (updatedData.getLocations() != null) {

            updatedData.getLocations().forEach(location -> {

                location.setCompany(company);

                company.getLocations().add(location);
            });

            logger.debug(
                    "Company locations updated. Company ID: {}, Location count: {}",
                    companyId,
                    updatedData.getLocations().size()
            );

        } else {

            logger.debug(
                    "No locations provided for company update. Company ID: {}",
                    companyId
            );
        }

        // Save
        Company savedCompany =
                companyRepository.save(company);

        logger.info(
                "Company updated successfully. Company ID: {}",
                savedCompany.getCompanyId()
        );

        // Return DTO
        return CompanyRowMapper.toDto(savedCompany);
    }


    @Transactional
    public void deleteCompany(
            Long companyId,
            Long userId) {

        logger.info(
                "Starting company deletion. Company ID: {}, User ID: {}",
                companyId,
                userId
        );

        // Find company
        Company company =
                companyRepository.findById(companyId)
                        .orElseThrow(() -> {

                            logger.warn(
                                    "Company deletion failed. Company not found. Company ID: {}",
                                    companyId
                            );

                            return new CustomRuntimeException(
                                    "Company not found",
                                    HttpStatus.NOT_FOUND
                            );
                        });

        // Check ownership
        if (!Objects.equals(
                company.getCreatedBy(),
                userId)) {

            logger.warn(
                    "Unauthorized company deletion attempt. User ID: {}, Company ID: {}",
                    userId,
                    companyId
            );

            throw new CustomRuntimeException(
                    "You are not authorized to delete this company",
                    HttpStatus.FORBIDDEN
            );
        }

        // Delete company
        companyRepository.delete(company);

        logger.info(
                "Company deleted successfully. Company ID: {}",
                companyId
        );
    }


    @Transactional(readOnly = true)
    public Page<CompanyDTO> searchCompanies(
            CompanyFilterRequestDto request,
            Pageable pageable) {

        logger.info(
                "Searching companies. Company name: {}, State: {}, City: {}, Country: {}, Page: {}, Size: {}",
                request.getCompanyName(),
                request.getState(),
                request.getCity(),
                request.getCountry(),
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        Page<Company> companies =
                companyRepository.searchCompanyBasedOnFilters(
                        request.getCompanyName(),
                        request.getState(),
                        request.getCity(),
                        request.getCountry(),
                        pageable
                );

        logger.info(
                "Company search completed. Total results: {}",
                companies.getTotalElements()
        );

        return companies.map(
                CompanyRowMapper::toDto
        );
    }
}