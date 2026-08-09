package com.job.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.dto.JobRequestDTO;
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
    public Job createJobPost(@RequestBody JobRequestDTO jobRequestDTO) {

        return jobService.createJobPost(jobRequestDTO);
    }
}