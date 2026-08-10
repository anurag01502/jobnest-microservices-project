package com.job.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserExternalService {
	
	
	@Value("${user-service.base-url}")
	private String USER_SERVICE_BASE_URL;
	
	private final RestClient userRestClient;
	
    public UserExternalService(
            @Qualifier("userRestClient") RestClient userRestClient) {

        this.userRestClient = userRestClient;
    }
    // GET
    /*public UserResponse getUser(Long id) {

        return restClient
                .get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(UserResponse.class);
    }

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
