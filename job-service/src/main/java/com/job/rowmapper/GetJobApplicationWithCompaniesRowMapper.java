package com.job.rowmapper;

import com.job.dto.GetJobApplicationWithCompaniesDto;
import com.job.dto.JobApplicationDto;
import com.job.model.Job;
import com.job.model.JobApplication;

public class GetJobApplicationWithCompaniesRowMapper {

    public static JobApplicationDto toDtoJobApplicationDto(
            GetJobApplicationWithCompaniesDto jobApplicationWithCompaniesDto) {

        if (jobApplicationWithCompaniesDto == null) {
            return null;
        }

        JobApplicationDto dto = new JobApplicationDto();

        dto.setApplicationId(
                jobApplicationWithCompaniesDto.getApplicationId());

        dto.setCandidateId(
                jobApplicationWithCompaniesDto.getCandidateId());

        dto.setStatus(
                jobApplicationWithCompaniesDto.getStatus());

        dto.setJobId(
                jobApplicationWithCompaniesDto.getJobId());

        dto.setAppliedAt(
                jobApplicationWithCompaniesDto.getAppliedAt());

        dto.setUpdatedAt(
                jobApplicationWithCompaniesDto.getUpdatedAt());

        return dto;
    }
    
    public static JobApplication toDtoJobApplicationModel(
            GetJobApplicationWithCompaniesDto jobApplicationWithCompaniesDto) {

        if (jobApplicationWithCompaniesDto == null) {
            return null;
        }

        JobApplication jobApplication = new JobApplication();

        jobApplication.setApplicationId(
                jobApplicationWithCompaniesDto.getApplicationId());

        jobApplication.setCandidateId(
                jobApplicationWithCompaniesDto.getCandidateId());

        jobApplication.setStatus(
                jobApplicationWithCompaniesDto.getStatus());

        jobApplication.setAppliedAt(
                jobApplicationWithCompaniesDto.getAppliedAt());

        jobApplication.setUpdatedAt(
                jobApplicationWithCompaniesDto.getUpdatedAt());

        // Convert jobId -> Job object
        if (jobApplicationWithCompaniesDto.getJobId() != null) {
            Job job = new Job();
            job.setJobId(jobApplicationWithCompaniesDto.getJobId());

            jobApplication.setJob(job);
        }

        return jobApplication;
    }
}