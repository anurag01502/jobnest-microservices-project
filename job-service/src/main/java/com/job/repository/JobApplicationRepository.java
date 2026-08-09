package com.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{

}
