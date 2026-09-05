package com.job.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.job.dto.CompanyDTO;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CompanyExternalService {

	private final RestClient companyRestClient;
	
    public CompanyExternalService(
            @Qualifier("companyRestClient") RestClient companyRestClient) {

        this.companyRestClient = companyRestClient;
    }
    
    public CompanyDTO getCompanyById(Long companyId) {
    	
        // Get token from current request
        HttpServletRequest request =
                ((ServletRequestAttributes)
                        RequestContextHolder.getRequestAttributes())
                        .getRequest();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

    return companyRestClient
            .get()
            .uri("/company/{companyId}", companyId)
            .header(HttpHeaders.AUTHORIZATION, token)
            .retrieve()
            .body(CompanyDTO.class);
	}
    
    
}
