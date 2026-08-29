package com.company.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

public class CompanyRegisterRequestDto {

    @NotBlank(message = "Company name is required")
    private String companyName;

    private Integer establishedYear;

    private String phone;

    @Email(message = "Invalid email")
    private String email;

    private String websiteUrl;

    private String description;

    @Positive(message = "Company size must be greater than 0")
    private Integer companySize;

    @Valid
    private List<CompanyLocationDTO> locations = new ArrayList<>();

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getEstablishedYear() {
		return establishedYear;
	}

	public void setEstablishedYear(Integer establishedYear) {
		this.establishedYear = establishedYear;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCompanySize() {
		return companySize;
	}

	public void setCompanySize(Integer companySize) {
		this.companySize = companySize;
	}

	public List<CompanyLocationDTO> getLocations() {
		return locations;
	}

	public void setLocations(List<CompanyLocationDTO> locations) {
		this.locations = locations;
	}
    
    
    
}
