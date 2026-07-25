package com.company.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.CompanyCardInfoDto;
import com.company.service.CompanyCardService;

@RequestMapping("/company")
@RestController
public class CompanyController {

	private final CompanyCardService companyCardService;
	
	public CompanyController(CompanyCardService companyCardService)
	{
		this.companyCardService = companyCardService;
		
		
	}
	
	@GetMapping("/list")
	public ResponseEntity<Page<CompanyCardInfoDto>> getCompanyCards(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {

	    Pageable pageable = PageRequest.of(page, size);

	    return ResponseEntity.ok(companyCardService.getCompanyCards(pageable));
	}
}
