package com.job.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.job.dto.JobDTO;
import com.job.model.Job;
import com.job.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PreAuthorize("hasRole('Recruiter')")
    @PostMapping("/create-post")
    public Job createJobPost(@RequestBody JobDTO jobRequestDTO) {

        return jobService.createJobPost(jobRequestDTO);
    }
    
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/view-posts")
    public Page<JobDTO> viewAvailableJobs(
            @RequestBody JobDTO filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return jobService.viewAvailableJobPosts(filter, page, size);
    }
}