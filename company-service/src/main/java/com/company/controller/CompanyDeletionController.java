package com.company.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.UserProfileResponseExternalDto;
import com.company.service.CompanyService;
import com.company.service.UserExternalService;

@RequestMapping("/company")
@RestController
public class CompanyDeletionController {

    private final CompanyService companyService;

    private final UserExternalService userExternalService;


    public CompanyDeletionController(
            CompanyService companyService,
            UserExternalService userExternalService) {

        this.companyService = companyService;
        this.userExternalService = userExternalService;
    }


    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(
            @PathVariable Long companyId,
            Authentication authentication) {

        UserProfileResponseExternalDto userProfile =
                userExternalService.getUser(
                        authentication.getName()
                );

        Long userId = userProfile.getUserId();

        companyService.deleteCompany(
                companyId,
                userId
        );

        return ResponseEntity.noContent().build();
    }
}