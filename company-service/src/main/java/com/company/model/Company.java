package com.company.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")


public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "established_year")
    private Integer establishedYear;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "company_size")
    private Integer companySize;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(
        mappedBy = "company",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<CompanyLocation> locations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "companies_available_domains",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "domain_id")
    )
    private List<BusinessDomain> domains = new ArrayList<>();

    @OneToOne(
        mappedBy = "company",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private CompanyStatistics statistics;

    @OneToOne(
        mappedBy = "company",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private VerificationStatus verificationStatus;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

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

	public List<CompanyLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<CompanyLocation> locations) {
		this.locations = locations;
	}

	public List<BusinessDomain> getDomains() {
		return domains;
	}

	public void setDomains(List<BusinessDomain> domains) {
		this.domains = domains;
	}

	public CompanyStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(CompanyStatistics statistics) {
		this.statistics = statistics;
	}

	public VerificationStatus getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(VerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
    
    
}