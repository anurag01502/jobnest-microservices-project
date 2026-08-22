package com.job.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.job.model.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

	
	@Query("""
		    SELECT j FROM Job j
		    WHERE (:companyId IS NULL OR j.companyId = :companyId)
		    AND (:title IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%')))
		    AND (:employmentType IS NULL OR j.employmentType = :employmentType)
		    AND (:workMode IS NULL OR j.workMode = :workMode)
		    AND (:experienceMin IS NULL OR j.experienceMax >= :experienceMin)
		    AND (:experienceMax IS NULL OR j.experienceMin <= :experienceMax)
		    AND (:salaryMin IS NULL OR j.salaryMax >= :salaryMin)
		    AND (:salaryMax IS NULL OR j.salaryMin <= :salaryMax)
		    AND (:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')))
		    AND (:status IS NULL OR j.status = :status)
		    AND (:applicationDeadline IS NULL OR j.applicationDeadline <= :applicationDeadline)
		""")
		Page<Job> findJobsWithFilters(
		        @Param("companyId") Long companyId,
		        @Param("title") String title,
		        @Param("employmentType") String employmentType,
		        @Param("workMode") String workMode,
		        @Param("experienceMin") BigDecimal experienceMin,
		        @Param("experienceMax") BigDecimal experienceMax,
		        @Param("salaryMin") BigDecimal salaryMin,
		        @Param("salaryMax") BigDecimal salaryMax,
		        @Param("location") String location,
		        @Param("status") String status,
		        @Param("applicationDeadline") LocalDate applicationDeadline,
		        Pageable pageable
		);
}
