package com.company.controller;

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

        UserProfileResponseExternalDto userProfile =
                userExternalService.getUser(
                        authentication.getName()
                );

        request.setCreatedBy(userProfile.getUserId());

        CompanyDTO company =
                companyService.updateCompany(
                        companyId,
                        request
                );

        return ResponseEntity.ok(company);
    }


    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<CompanyDTO> registerCompany(
            @Valid @RequestBody CompanyRegisterRequestDto request,
            Authentication authentication) {

        UserProfileResponseExternalDto userProfile =
                userExternalService.getUser(
                        authentication.getName()
                );

        request.setCreatedBy(userProfile.getUserId());

        CompanyDTO company =
                companyService.registerCompany(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(company);
    }
}
