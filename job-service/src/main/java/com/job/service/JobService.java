package com.job.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.job.dto.JobDTO;
import com.job.dto.UserProfileResponseExternalDto;
import com.job.exception.CustomRuntimeException;
import com.job.model.Job;
import com.job.repository.JobRepository;
import com.job.rowmapper.JobRowmapper;

import jakarta.transaction.Transactional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    private final CompanyExternalService companyExternalService;
    
    public JobService(JobRepository jobRepository,CompanyExternalService companyExternalService) {
        this.jobRepository = jobRepository;
		this.companyExternalService = companyExternalService;
    }

    
    @Transactional
    public Job createJobPost(JobDTO jobRequestDTO) {


        // Validate that company exists in Company Service
    	companyExternalService.getCompanyById(jobRequestDTO.getCompanyId() );
    	
    	
        Job job = JobRowmapper.toModel(jobRequestDTO);

        
        return jobRepository.save(job);
    }
    
    
    public Page<JobDTO> viewAvailableJobPosts(
            JobDTO filter,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Job> jobList = jobRepository.findJobsWithFilters(
                filter.getTitle(),
                filter.getEmploymentType(),
                filter.getWorkMode(),
                filter.getExperienceMin(),
                filter.getExperienceMax(),
                filter.getSalaryMin(),
                filter.getSalaryMax(),
                filter.getLocation(),
                filter.getStatus(),
                filter.getApplicationDeadline(),
                pageable
        );

        return jobList.map(JobRowmapper::toDto);
    }
    
    public void deleteAJob(UserProfileResponseExternalDto userResponseData, long jobId) {

        // Get the job
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Get the logged-in user's ID
        Long userId = userResponseData.getUserId();

        // Check whether the user created this job
        if (!job.getCreatorId().equals(userId)) {
            throw new CustomRuntimeException("You are not authorized to delete this job"
            		,HttpStatus.FORBIDDEN);
        }

        // User is the creator, so delete the job
        jobRepository.deleteById(jobId);
    }
}