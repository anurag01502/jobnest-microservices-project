package com.job.service;

import org.springframework.stereotype.Service;

import com.job.dto.JobRequestDTO;
import com.job.model.Job;
import com.job.repository.JobRepository;
import com.job.rowmapper.JobRowmapper;

@Service
public class JobService {

    private final JobRepository jobRepository;

    private final CompanyExternalService companyExternalService;
    
    public JobService(JobRepository jobRepository,CompanyExternalService companyExternalService) {
        this.jobRepository = jobRepository;
		this.companyExternalService = companyExternalService;
    }

    public Job createJobPost(JobRequestDTO jobRequestDTO) {


        // Validate that company exists in Company Service
    	companyExternalService.getCompanyById(jobRequestDTO.getCompanyId() );
    	
    	
        Job job = JobRowmapper.toModel(jobRequestDTO);

        
        return jobRepository.save(job);
    }
}