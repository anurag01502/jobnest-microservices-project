package com.job.rowmapper;

import com.job.dto.JobDTO;
import com.job.model.Job;

public class JobRowmapper {

    public static JobDTO toDto(Job job) {

        JobDTO dto = new JobDTO();

        dto.setJobId(job.getJobId());
        dto.setCompanyId(job.getCompanyId());
        dto.setCreatorId(job.getCreatorId());

        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());

        dto.setEmploymentType(job.getEmploymentType());
        dto.setWorkMode(job.getWorkMode());

        dto.setExperienceMin(job.getExperienceMin());
        dto.setExperienceMax(job.getExperienceMax());

        dto.setSalaryMin(job.getSalaryMin());
        dto.setSalaryMax(job.getSalaryMax());
        dto.setSalaryCurrency(job.getSalaryCurrency());

        dto.setLocation(job.getLocation());
        dto.setOpenings(job.getOpenings());
        dto.setStatus(job.getStatus());

        dto.setApplicationDeadline(job.getApplicationDeadline());



        return dto;
    }

    public static Job toModel(JobDTO dto) {

        Job job = new Job();

        job.setJobId(dto.getJobId());
        job.setCompanyId(dto.getCompanyId());
        job.setCreatorId(dto.getCreatorId());

        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());

        job.setEmploymentType(dto.getEmploymentType());
        job.setWorkMode(dto.getWorkMode());

        job.setExperienceMin(dto.getExperienceMin());
        job.setExperienceMax(dto.getExperienceMax());

        job.setSalaryMin(dto.getSalaryMin());
        job.setSalaryMax(dto.getSalaryMax());
        job.setSalaryCurrency(dto.getSalaryCurrency());

        job.setLocation(dto.getLocation());
        job.setOpenings(dto.getOpenings());
        job.setStatus(dto.getStatus());

        job.setApplicationDeadline(dto.getApplicationDeadline());

        return job;
    }
}