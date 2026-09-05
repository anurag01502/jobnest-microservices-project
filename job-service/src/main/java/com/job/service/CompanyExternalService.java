package com.job.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.job.dto.CompanyDTO;
import com.job.exception.CustomRuntimeException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CompanyExternalService {

	private final RestClient companyRestClient;
	
    public CompanyExternalService(
            @Qualifier("companyRestClient") RestClient companyRestClient) {

        this.companyRestClient = companyRestClient;
    }
    
    public CompanyDTO getCompanyById(Long companyId) {

        try {

            // Get token from current request
            HttpServletRequest request =
                    ((ServletRequestAttributes)
                            RequestContextHolder.getRequestAttributes())
                            .getRequest();

            String token =
                    request.getHeader(HttpHeaders.AUTHORIZATION);

            return companyRestClient
                    .get()
                    .uri("/company/{companyId}", companyId)
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .body(CompanyDTO.class);

        } catch (HttpClientErrorException.BadRequest ex) {

            throw new CustomRuntimeException(
                    "Bad request!",
                    HttpStatus.BAD_REQUEST
            );

        } catch (HttpClientErrorException.Unauthorized ex) {

            throw new CustomRuntimeException(
                    "Unauthorized!",
                    HttpStatus.UNAUTHORIZED
            );

        } catch (HttpClientErrorException.Forbidden ex) {

            throw new CustomRuntimeException(
                    "Access denied!",
                    HttpStatus.FORBIDDEN
            );

        } catch (HttpClientErrorException.NotFound ex) {

            throw new CustomRuntimeException(
                    "Company does not exist!",
                    HttpStatus.NOT_FOUND
            );

        } catch (HttpServerErrorException.InternalServerError ex) {

            throw new CustomRuntimeException(
                    "Company service internal server error!",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    
    
}
