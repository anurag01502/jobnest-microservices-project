package com.company.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.dto.UserProfileResponseExternalDto;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class UserExternalService {
	
	

	
	private final RestClient userRestClient;
	
	
    public UserExternalService(
            @Qualifier("userRestClient") RestClient userRestClient) {

        this.userRestClient = userRestClient;
    }
    
    public UserProfileResponseExternalDto getUser(String identifier) {




        // Get token from current request
        HttpServletRequest request =
                ((ServletRequestAttributes)
                        RequestContextHolder.getRequestAttributes())
                        .getRequest();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        return userRestClient
                .get()
                .uri("/api/users/{identifier}", identifier)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .body(UserProfileResponseExternalDto.class);
    }
/*
    // POST
    public UserResponse createUser(UserRequest request) {

        return restClient
                .post()
                .uri("/users")
                .body(request)
                .retrieve()
                .body(UserResponse.class);
    }

    // PUT
    public UserResponse updateUser(Long id, UserRequest request) {

        return restClient
                .put()
                .uri("/users/{id}", id)
                .body(request)
                .retrieve()
                .body(UserResponse.class);
    }

    // PATCH
    public UserResponse patchUser(Long id, UserRequest request) {

        return restClient
                .patch()
                .uri("/users/{id}", id)
                .body(request)
                .retrieve()
                .body(UserResponse.class);
    }

    // DELETE
    public void deleteUser(Long id) {

        restClient
                .delete()
                .uri("/users/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }*/
	
	
}
