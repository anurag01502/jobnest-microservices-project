package com.company.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.CompanyCardInfoDto;
import com.company.dto.CompanyDTO;
import com.company.dto.CompanyFilterRequestDto;
import com.company.service.CompanyCardService;
import com.company.service.CompanyService;

@RequestMapping("/company")
@RestController
public class CompanyViewLevelController {

	private final CompanyCardService companyCardService;
	private final CompanyService companyService;
	public CompanyViewLevelController(CompanyCardService companyCardService,
			 CompanyService companyService)
	{
		this.companyCardService = companyCardService;
		this.companyService = companyService;
		
		
	}
	@PreAuthorize("hasAnyRole('USER','SUPER_ADMIN','RECRUITER','COMPANY_ADMIN')")
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
	@PreAuthorize("hasAnyRole('USER','SUPER_ADMIN','RECRUITER','COMPANY_ADMIN')")
	@PostMapping("/search")
	public ResponseEntity<Page<CompanyDTO>> searchCompanies(
	        @RequestBody CompanyFilterRequestDto request,
	        @PageableDefault(size = 10) Pageable pageable) {

	    Page<CompanyDTO> result =
	            companyService.searchCompanies(request, pageable);

	    return ResponseEntity.ok(result);
	}
	
}
