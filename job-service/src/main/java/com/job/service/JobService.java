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


    public JobService(
            JobRepository jobRepository,
            CompanyExternalService companyExternalService) {

        this.jobRepository = jobRepository;
        this.companyExternalService = companyExternalService;
    }


    // ==========================================
    // CREATE JOB
    // ==========================================

    @Transactional
    public Job createJobPost(
            JobDTO jobRequestDTO,
            UserProfileResponseExternalDto userResponseData) {

        // Get logged-in user's ID
        Long userId = userResponseData.getUserId();


        // Check whether company exists
        companyExternalService.getCompanyById(
                jobRequestDTO.getCompanyId()
        );


        // Convert DTO to model
        Job job = JobRowmapper.toModel(jobRequestDTO);


        // Set creator from authenticated user
        job.setCreatorId(userId);


        // Save job
        return jobRepository.save(job);
    }


    // ==========================================
    // VIEW AVAILABLE JOBS
    // ==========================================

    public Page<JobDTO> viewAvailableJobPosts(
            JobDTO filter,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Job> jobList =
                jobRepository.findJobsWithFilters(
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


    // ==========================================
    // DELETE JOB
    // ==========================================

    @Transactional
    public void deleteAJob(
            UserProfileResponseExternalDto userResponseData,
            long jobId) {

        // Get job
        Job job =
                jobRepository.findById(jobId)
                        .orElseThrow(() ->
                                new CustomRuntimeException(
                                        "Job not found",
                                        HttpStatus.NOT_FOUND
                                )
                        );


        // Get logged-in user's ID
        Long userId =
                userResponseData.getUserId();


        // Check ownership
        if (!job.getCreatorId().equals(userId)) {

            throw new CustomRuntimeException(
                    "You are not authorized to delete this job",
                    HttpStatus.FORBIDDEN
            );
        }


        // Delete job
        jobRepository.delete(job);
    }
}