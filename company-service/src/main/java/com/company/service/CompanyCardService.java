package com.company.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.company.dto.CompanyCardInfoDto;
import com.company.dto.CompanyDTO;
import com.company.exception.CustomRuntimeException;
import com.company.model.Company;
import com.company.repository.CompanyCardRepository;
import com.company.rowmapper.CompanyRowMapper;


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
    
    public CompanyDTO getCompanyById(Long companyId )
    {
    	
    	Company getCompanyById= companyRepository.findById(companyId).orElseThrow(()-> 
    	new CustomRuntimeException("Company not found! ", HttpStatus.NOT_FOUND) );
    	
    	
    	return CompanyRowMapper.toDto(getCompanyById);
    
    	    
    }
    
}
