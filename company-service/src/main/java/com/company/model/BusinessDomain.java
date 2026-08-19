package com.company.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "business_domain")


public class BusinessDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domain_id")
    private Long domainId;

    @Column(name = "domain_name", nullable = false, unique = true)
    private String domainName;

    @ManyToMany(mappedBy = "domains")
    private List<Company> companies;

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
    
    
    
    
}