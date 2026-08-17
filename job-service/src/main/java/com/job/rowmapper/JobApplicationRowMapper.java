package com.job.rowmapper;

import com.job.dto.JobApplicationDto;
import com.job.model.Job;
import com.job.model.JobApplication;

public class JobApplicationRowMapper {

    public static JobApplicationDto toDto(JobApplication application) {

        JobApplicationDto dto = new JobApplicationDto();

        dto.setApplicationId(application.getApplicationId());
        dto.setCandidateId(application.getCandidateId());
        dto.setStatus(application.getStatus());

        if (application.getJob() != null) {
            dto.setJobId(application.getJob().getJobId());
        }

        dto.setAppliedAt(application.getAppliedAt());
        dto.setUpdatedAt(application.getUpdatedAt());

        return dto;
    }

    public static JobApplication toModel(JobApplicationDto dto) {

        JobApplication application = new JobApplication();

        application.setApplicationId(dto.getApplicationId());
        application.setCandidateId(dto.getCandidateId());
        application.setStatus(dto.getStatus());

        Job job = new Job();
        job.setJobId(dto.getJobId());

        application.setJob(job);

        application.setAppliedAt(dto.getAppliedAt());
        application.setUpdatedAt(dto.getUpdatedAt());

        return application;
    }
}