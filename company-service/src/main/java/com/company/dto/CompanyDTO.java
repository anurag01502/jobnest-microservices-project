package com.company.dto;


import java.time.LocalDateTime;

public class CompanyDTO {

    private Long companyId;
    private String companyName;
    private Integer establishedYear;
    private String phone;
    private String email;
    private String websiteUrl;
    private String description;
    private Integer companySize;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
    
}