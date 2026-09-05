package com.authservice.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authservice.dao.UserDao;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger logger =
            LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtUtil jwtUtil;
    private final UserDao userDao;
    private final CustomUserDetailsService userDetailsService;


    public JwtAuthFilter(
            JwtUtil jwtUtil,
            CustomUserDetailsService userDetailsService,
            UserDao userDao) {

        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userDao = userDao;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");


        if (authHeader != null
                && authHeader.startsWith("Bearer ")) {

            String token =
                    authHeader.substring(7);

            try {

                // Check blacklist
                if (userDao.isBlackListed(token)) {

                    response.setStatus(
                            HttpServletResponse.SC_UNAUTHORIZED
                    );

                    return;
                }


                // Extract username
                String username =
                        jwtUtil.extractUsername(token);


                if (username != null
                        && SecurityContextHolder
                                .getContext()
                                .getAuthentication() == null) {

                    UserDetails userDetails =
                            userDetailsService
                                    .loadUserByUsername(username);


                    // Validate ACCESS token
                    if (jwtUtil.validateToken(
                            token,
                            userDetails.getUsername())) {

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        SecurityContextHolder
                                .getContext()
                                .setAuthentication(authToken);
                    }
                }

            } catch (JwtException
                    | IllegalArgumentException e) {

                logger.warn(
                        "Invalid JWT: {}",
                        e.getMessage()
                );

                response.setStatus(
                        HttpServletResponse.SC_UNAUTHORIZED
                );

                return;
            }
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}