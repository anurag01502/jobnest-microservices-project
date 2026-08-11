package com.job.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestClient;

public class CompanyExternalService {

	private final RestClient companyRestClient;
	
    public CompanyExternalService(
            @Qualifier("companyRestClient") RestClient companyRestClient) {

        this.companyRestClient = companyRestClient;
    }
}
