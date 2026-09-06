package com.company.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.CompanyDTO;
import com.company.dto.CompanyRegisterRequestDto;
import com.company.service.CompanyService;

import jakarta.validation.Valid;

@RequestMapping("/company")
@RestController
public class CompanyRegistrationAndUpdationController {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    CompanyRegistrationAndUpdationController.class
            );

    private final CompanyService companyService;

    public CompanyRegistrationAndUpdationController(
            CompanyService companyService) {

        this.companyService = companyService;
    }


    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyDTO> updateCompany(

            @PathVariable Long companyId,

            @Valid
            @RequestBody
            CompanyRegisterRequestDto request,

            Authentication authentication) {

        logger.info(
                "Received request to update company with ID: {}",
                companyId
        );

        CompanyDTO company =
                companyService.updateCompany(
                        companyId,
                        request,
                        authentication
                );

        logger.info(
                "Company updated successfully. Company ID: {}",
                companyId
        );

        return ResponseEntity.ok(company);
    }


    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<CompanyDTO> registerCompany(

            @Valid
            @RequestBody
            CompanyRegisterRequestDto request,

            Authentication authentication) {

        logger.info(
                "Received request to register a new company"
        );

        CompanyDTO company =
                companyService.registerCompany(
                        request,
                        authentication
                );

        logger.info(
                "Company registered successfully. Company ID: {}",
                company.getCompanyId()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(company);
    }
}