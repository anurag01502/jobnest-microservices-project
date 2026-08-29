package com.company.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/company")
@RestController
public class CompanyDeletionController {

	@PreAuthorize("hasRole('COMPANY_ADMIN')") 
	@GetMapping("/delete")
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	
}
