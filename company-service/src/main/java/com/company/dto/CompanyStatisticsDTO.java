package com.company.dto;

import java.math.BigDecimal;

import com.company.model.Company;

public class CompanyStatisticsDTO {

    private Long companyId;

    private Company company;

    private Integer projectsCompleted;

    private BigDecimal averageRating;

    private BigDecimal averageTime;

    private Integer totalClients;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Integer getProjectsCompleted() {
		return projectsCompleted;
	}

	public void setProjectsCompleted(Integer projectsCompleted) {
		this.projectsCompleted = projectsCompleted;
	}

	public BigDecimal getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(BigDecimal averageRating) {
		this.averageRating = averageRating;
	}

	public BigDecimal getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(BigDecimal averageTime) {
		this.averageTime = averageTime;
	}

	public Integer getTotalClients() {
		return totalClients;
	}

	public void setTotalClients(Integer totalClients) {
		this.totalClients = totalClients;
	}
    
    
}
