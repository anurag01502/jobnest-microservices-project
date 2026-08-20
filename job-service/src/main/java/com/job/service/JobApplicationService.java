package com.job.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.job.dto.GetJobApplicationWithCompaniesDto;
import com.job.dto.JobApplicationDto;
import com.job.dto.UserProfileResponseExternalDto;
import com.job.exception.CustomRuntimeException;
import com.job.model.JobApplication;
import com.job.repository.JobApplicationRepository;
import com.job.repository.JobRepository;
import com.job.rowmapper.GetJobApplicationWithCompaniesRowMapper;
import com.job.rowmapper.JobApplicationRowMapper;
import jakarta.transaction.Transactional;

@Service
public class JobApplicationService {

   // private final CompanyExternalService companyExternalService;

    private final JobApplicationRepository jobApplicationRepository;
	
	private final JobRepository jobRepository;
	private final UserExternalService userExternalService;

	private final CompanyExternalService companyExternalService;

  
	
	public JobApplicationService(
			JobRepository jobRepository,
			UserExternalService userExternalService,
			JobApplicationRepository jobApplicationRepository
			,CompanyExternalService companyExternalService
			)
	
	{
		this.jobApplicationRepository = jobApplicationRepository;
		this.jobRepository = jobRepository;
		this.userExternalService = userExternalService;
		this.companyExternalService = companyExternalService;
		
		
	}
	
	@Transactional
	public JobApplication  applyForJob(JobApplicationDto jobApplicationRequest,Authentication authentication)
	{
		
		UserProfileResponseExternalDto userProfileResponseExternalDto= userExternalService.getUser(authentication.getName()); // validates the user existance
		
		 jobRepository.findById(jobApplicationRequest.getJobId()).orElseThrow(()-> new
				 
				 CustomRuntimeException("Job does not exists", HttpStatus.NOT_FOUND)); // checks if job post exists 
		
		jobApplicationRequest.setCandidateId(userProfileResponseExternalDto.getUserId());
		
		return jobApplicationRepository.save(JobApplicationRowMapper.toModel(jobApplicationRequest));
	}
	
	
	public Page<JobApplicationDto> getMyJobApplication(Authentication authentication,
	        int page,
	        int size) {

	    Pageable pageable = PageRequest.of(page, size);

	    UserProfileResponseExternalDto userResponse= userExternalService.getUser(authentication.getName());
	    Page<JobApplication> applicationList =
	            jobApplicationRepository.viewMyJobApplications(pageable, userResponse.getUserId());

	    
	    
	    
	    
	 
	 
	    	
	    return applicationList.map(application -> {

	    	
	    	JobApplicationDto dto= JobApplicationRowMapper.toDto(application);

	        if (application.getJob() != null) {
	            dto.setJobId(application.getJob().getJobId());
	        }
	        
	        

	        return dto;
	    });
	}
	

}
