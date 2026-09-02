package com.job.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.job.model.JobApplication;
import io.lettuce.core.dynamic.annotation.Param;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{

	
	
	
	@Query("""
		    SELECT jba
		    FROM JobApplication jba
		    WHERE jba.candidateId = :userId
		    """)
		Page<JobApplication> viewMyJobApplications(
		    Pageable pageable,
		    @Param("userId") Long userId
		);
}
