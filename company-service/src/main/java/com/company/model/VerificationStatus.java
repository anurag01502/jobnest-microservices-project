package com.company.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification_status")
@NoArgsConstructor
public class VerificationStatus {

    @Id
    @Column(name = "company_id")
    private Long companyId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(
        name = "company_id",
        foreignKey = @ForeignKey(name = "fk_verification_company")
    )
    private Company company;

    @Column(name = "status")
    private String status ;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @Column(name = "verified_by")
    private String verifiedBy;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(LocalDateTime verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
    
    
}
