package com.company.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/company")
@RestController
public class CompanyRegistrationAndUpdationController {
	
	
	@GetMapping("/update")
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	

}
