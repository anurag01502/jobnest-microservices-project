package com.job.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.job.dto.JobDTO;
import com.job.dto.UserProfileResponseExternalDto;
import com.job.model.Job;
import com.job.service.JobService;
import com.job.service.UserExternalService;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    private final UserExternalService userExternalService;
    public JobController(JobService jobService,UserExternalService userExternalService) {
        this.jobService = jobService;
		this.userExternalService = userExternalService;
    }

    @PreAuthorize("hasRole('Recruiter')")
    @PostMapping("/create-post")
    public Job createJobPost(@RequestBody JobDTO jobRequestDTO) {

        return jobService.createJobPost(jobRequestDTO);
    }
    
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/view-posts")
    public Page<JobDTO> viewAvailableJobs(
            @RequestBody JobDTO filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return jobService.viewAvailableJobPosts(filter, page, size);
    }
    
	@PreAuthorize("hasAnyRole('Recruiter','Company Admin')")
	@DeleteMapping("/{jobId}")
	public ResponseEntity<Void> deleteMyJob(Authentication authentication,@PathVariable("jobId") long jobId)
	{
		
		UserProfileResponseExternalDto userDataResponse = userExternalService.getUser(authentication.getName());
		
		 jobService.deleteAJob(userDataResponse,jobId);
		 
		 return ResponseEntity.noContent().build();
		
	}
}