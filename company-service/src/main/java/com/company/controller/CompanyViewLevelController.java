package com.company.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.CompanyCardInfoDto;
import com.company.dto.CompanyDTO;
import com.company.service.CompanyCardService;

@RequestMapping("/company")
@RestController
public class CompanyViewLevelController {

	private final CompanyCardService companyCardService;
	
	public CompanyViewLevelController(CompanyCardService companyCardService)
	{
		this.companyCardService = companyCardService;
		
		
	}
	@PreAuthorize("hasAnyRole('USER','SUPER_ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<Page<CompanyCardInfoDto>> getCompanyCards(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {

	    Pageable pageable = PageRequest.of(page, size);

	    return ResponseEntity.ok(companyCardService.getCompanyCards(pageable));
	}
	@PreAuthorize("hasAnyRole('USER','SUPER_ADMIN','RECRUITER','COMPANY_ADMIN')")
	@GetMapping("/{companyId}")
	public ResponseEntity<CompanyDTO> getCompanyById(
	        @PathVariable Long companyId) {

	    return ResponseEntity.ok(
	            companyCardService.getCompanyById(companyId)
	    );
	}
	
}
