package com.company.rowmapper;

import com.company.dto.CompanyDTO;
import com.company.model.Company;

public class CompanyRowMapper {



	public static  Company toModel(CompanyDTO dto) {

	    Company company = new Company();

	    company.setCompanyId(dto.getCompanyId());
	    company.setCompanyName(dto.getCompanyName());
	    company.setEstablishedYear(dto.getEstablishedYear());
	    company.setPhone(dto.getPhone());
	    company.setEmail(dto.getEmail());
	    company.setWebsiteUrl(dto.getWebsiteUrl());
	    company.setDescription(dto.getDescription());
	    company.setCompanySize(dto.getCompanySize());
	    company.setCreatedAt(dto.getCreatedAt());
	    company.setUpdatedAt(dto.getUpdatedAt());

	    return company;
	}
	
	public static  CompanyDTO toDto(Company company) {

	    CompanyDTO dto = new CompanyDTO();

	    dto.setCompanyId(company.getCompanyId());
	    dto.setCompanyName(company.getCompanyName());
	    dto.setEstablishedYear(company.getEstablishedYear());
	    dto.setPhone(company.getPhone());
	    dto.setEmail(company.getEmail());
	    dto.setWebsiteUrl(company.getWebsiteUrl());
	    dto.setDescription(company.getDescription());
	    dto.setCompanySize(company.getCompanySize());
	    dto.setCreatedAt(company.getCreatedAt());
	    dto.setUpdatedAt(company.getUpdatedAt());

	    return dto;
	}
}