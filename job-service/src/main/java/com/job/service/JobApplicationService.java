package com.job.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.job.dto.JobApplicationRequestDto;
import com.job.dto.UserProfileResponseExternalDto;
import com.job.exception.CustomRuntimeException;
import com.job.repository.JobApplicationRepository;
import com.job.repository.JobRepository;
import com.job.rowmapper.JobApplicationRowMapper;

@Service
public class JobApplicationService {

   // private final CompanyExternalService companyExternalService;

    private final JobApplicationRepository jobApplicationRepository;
	
	private final JobRepository jobRepository;
	private final UserExternalService userExternalService;


  
	
	public JobApplicationService(
			JobRepository jobRepository,
			UserExternalService userExternalService,
			JobApplicationRepository jobApplicationRepository
			)
	
	{
		this.jobApplicationRepository = jobApplicationRepository;
		this.jobRepository = jobRepository;
		this.userExternalService = userExternalService;
		
		
	}
	
	
	public void  applyForJob(JobApplicationRequestDto jobApplicationRequest,Authentication authentication)
	{
		
		UserProfileResponseExternalDto userProfileResponseExternalDto= userExternalService.getUser(authentication.getName()); // validates the user existance
		
		 jobRepository.findById(jobApplicationRequest.getJobId()).orElseThrow(()-> new
				 
				 CustomRuntimeException("Job does not exists", HttpStatus.NOT_FOUND)); // checks if job post exists 
		
		jobApplicationRequest.setCandidateId(userProfileResponseExternalDto.getUserId());
		
		jobApplicationRepository.save(JobApplicationRowMapper.toModel(jobApplicationRequest));
	}
	

}
