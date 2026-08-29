package com.company.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.CompanyDTO;
import com.company.dto.CompanyRegisterRequestDto;
import com.company.service.CompanyService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/company")
@RestController
public class CompanyRegistrationAndUpdationController {
	
	
	private final CompanyService companyService;
	
	
	public CompanyRegistrationAndUpdationController(CompanyService companyService)
	{
		this.companyService = companyService;
		
	}
	

	
	@PreAuthorize("hasRole('COMPANY_ADMIN')") 
	@PostMapping("/register") 
	public ResponseEntity<CompanyDTO> registerCompany( @Valid @RequestBody CompanyRegisterRequestDto request) 
	{
		
		CompanyDTO company = companyService.registerCompany(request); 
	return ResponseEntity .status(HttpStatus.CREATED) .body(company);
	}
	

}
