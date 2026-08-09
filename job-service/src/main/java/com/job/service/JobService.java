package com.job.service;

import org.springframework.stereotype.Service;

import com.job.dto.JobRequestDTO;
import com.job.model.Job;
import com.job.repository.JobRepository;
import com.job.rowmapper.JobRowmapper;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJobPost(JobRequestDTO jobRequestDTO) {

        Job job = JobRowmapper.toModel(jobRequestDTO);

        return jobRepository.save(job);
    }
}