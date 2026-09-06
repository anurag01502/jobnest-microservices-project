package com.company.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.CompanyDTO;
import com.company.dto.CompanyRegisterRequestDto;
import com.company.dto.UserProfileResponseExternalDto;
import com.company.service.CompanyService;
import com.company.service.UserExternalService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/company")
@RestController
public class CompanyRegistrationAndUpdationController {

    private static final Logger logger =
            LoggerFactory.getLogger(CompanyRegistrationAndUpdationController.class);

    private final CompanyService companyService;
    private final UserExternalService userExternalService;

    public CompanyRegistrationAndUpdationController(
            CompanyService companyService,
            UserExternalService userExternalService) {

        this.companyService = companyService;
        this.userExternalService = userExternalService;
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyDTO> updateCompany(
            @PathVariable Long companyId,
            @Valid @RequestBody CompanyRegisterRequestDto request,
            Authentication authentication) {

        logger.info("Received request to update company with ID: {}",
                companyId);

        UserProfileResponseExternalDto userProfile =
                userExternalService.getUser(authentication.getName());

        logger.debug("Authenticated user ID: {} is attempting to update company ID: {}",
                userProfile.getUserId(), companyId);

        request.setCreatedBy(userProfile.getUserId());

        CompanyDTO company =
                companyService.updateCompany(
                        companyId,
                        request,
                        authentication
                );

        logger.info("Company updated successfully. Company ID: {}",
                companyId);

        return ResponseEntity.ok(company);
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<CompanyDTO> registerCompany(
            @Valid @RequestBody CompanyRegisterRequestDto request,
            Authentication authentication) {

        logger.info("Received request to register a new company");

        UserProfileResponseExternalDto userProfile =
                userExternalService.getUser(
                        authentication.getName()
                );

        logger.debug("Authenticated user ID: {} is registering a company",
                userProfile.getUserId());

        request.setCreatedBy(userProfile.getUserId());

        CompanyDTO company =
                companyService.registerCompany(request);

        logger.info("Company registered successfully. Company ID: {}",
                company.getCompanyId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(company);
    }
}