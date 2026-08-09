package com.job.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "work_mode")
    private String workMode;

    @Column(name = "experience_min", precision = 4, scale = 1)
    private BigDecimal experienceMin;

    @Column(name = "experience_max", precision = 4, scale = 1)
    private BigDecimal experienceMax;

    @Column(name = "salary_min", precision = 12, scale = 2)
    private BigDecimal salaryMin;

    @Column(name = "salary_max", precision = 12, scale = 2)
    private BigDecimal salaryMax;

    @Column(name = "salary_currency", length = 10)
    private String salaryCurrency;

    @Column(length = 255)
    private String location;

    @Column
    private Integer openings;

    @Column(nullable = false)
    private String status;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "job")
    private List<JobApplication> jobApplications;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getWorkMode() {
		return workMode;
	}

	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}

	public BigDecimal getExperienceMin() {
		return experienceMin;
	}

	public void setExperienceMin(BigDecimal experienceMin) {
		this.experienceMin = experienceMin;
	}

	public BigDecimal getExperienceMax() {
		return experienceMax;
	}

	public void setExperienceMax(BigDecimal experienceMax) {
		this.experienceMax = experienceMax;
	}

	public BigDecimal getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(BigDecimal salaryMin) {
		this.salaryMin = salaryMin;
	}

	public BigDecimal getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(BigDecimal salaryMax) {
		this.salaryMax = salaryMax;
	}

	public String getSalaryCurrency() {
		return salaryCurrency;
	}

	public void setSalaryCurrency(String salaryCurrency) {
		this.salaryCurrency = salaryCurrency;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getOpenings() {
		return openings;
	}

	public void setOpenings(Integer openings) {
		this.openings = openings;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getApplicationDeadline() {
		return applicationDeadline;
	}

	public void setApplicationDeadline(LocalDate applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

	@PrePersist
	protected void onCreate() {
	    createdAt = LocalDateTime.now();
	    updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
	    updatedAt = LocalDateTime.now();
	}

  
}