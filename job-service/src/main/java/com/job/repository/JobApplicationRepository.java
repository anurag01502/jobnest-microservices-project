package com.job.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.job.model.JobApplication;
import io.lettuce.core.dynamic.annotation.Param;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{

	
	
	
	@Query("""
			SELECT 
			
			jba.applicationId as applicationId,
			jba.candidateId as candidateId,
			jba.status as status,
			jba.appliedAt as appliedAt
			from JobApplication Jba
			
			where jba.candidateId= :userId
			""")
	Page<JobApplication> viewMyJobApplications(Pageable pageable,@Param("userId") Long userId);
}
