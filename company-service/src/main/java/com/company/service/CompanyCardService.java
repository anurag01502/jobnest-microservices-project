package com.company.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.dto.CompanyCardInfoDto;
import com.company.repository.CompanyCardRepository;


@Service
public class CompanyCardService {

	
    private final CompanyCardRepository companyRepository;

    
    
    public CompanyCardService(CompanyCardRepository companyRepository)
    {
    	this.companyRepository =companyRepository;
    	
    }
    public Page<CompanyCardInfoDto> getCompanyCards(Pageable pageable) {

        return companyRepository.getCompanyCards(pageable)
                .map(company -> new CompanyCardInfoDto(
                        company.getCompanyName(),
                        company.getLocation(),
                        company.getRatings(),
                        company.getTotalClients()
                ));
    }
}
