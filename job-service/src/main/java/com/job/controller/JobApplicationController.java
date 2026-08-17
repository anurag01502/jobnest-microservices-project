package com.job.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.job.dto.JobApplicationDto;
import com.job.exception.CustomRuntimeException;
import com.job.model.JobApplication;
import com.job.service.JobApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/job")
public class JobApplicationController {

	private final JobApplicationService jobApplicationService;
	
	public JobApplicationController(JobApplicationService jobApplicationService)
	{
		this.jobApplicationService =jobApplicationService;
		
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/apply")
	public ResponseEntity<?>  applyJob(@RequestBody JobApplicationDto jobApplicationRequest,Authentication authentication )
	{
		
		
		JobApplication applyJobResponse =  jobApplicationService.applyForJob(jobApplicationRequest, authentication);
	
	
		if(applyJobResponse==null)
		{
			throw new CustomRuntimeException("failed to apply for job post", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Job with id "+applyJobResponse.getJob().getJobId()+" applied successfully!");
	
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/view-my-applications")
	public Page<JobApplicationDto> getMyJobApplication(Authentication authentication,
	        @RequestParam("page") int page,
	        @RequestParam("size") int size
	       ){
		
		
		return jobApplicationService.getMyJobApplication(authentication, page, size);
	}
	
	
	
	
}
