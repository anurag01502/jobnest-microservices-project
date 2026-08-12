package com.job.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.job.dto.CompanyDTO;

@Service
public class CompanyExternalService {

	private final RestClient companyRestClient;
	
    public CompanyExternalService(
            @Qualifier("companyRestClient") RestClient companyRestClient) {

        this.companyRestClient = companyRestClient;
    }
    
    public CompanyDTO getCompanyById(Long companyId) {

    return companyRestClient
            .get()
            .uri("/company/{companyId}", companyId)
            .retrieve()
            .body(CompanyDTO.class);
	}
    
    
}
