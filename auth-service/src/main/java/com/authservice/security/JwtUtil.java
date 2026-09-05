package com.authservice.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final Logger logger =
            LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long TOKEN_EXPIRATION;

    @Value("${jwt.refresh-expiration}")
    private long REFRESH_TOKEN_EXPIRATION;


    // Generate ACCESS token
    public String generateToken(String identifier, String role) {

        return Jwts.builder()
                .subject(identifier)
                .claim("role", role)
                .claim("type", "access")
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + TOKEN_EXPIRATION
                        )
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes(
                                        StandardCharsets.UTF_8
                                )
                        )
                )
                .compact();
    }


    public String refreshToken(String identifier) {

        return Jwts.builder()
                .subject(identifier)
                .claim("type", "refresh")
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + REFRESH_TOKEN_EXPIRATION
                        )
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
                        )
                )
                .compact();
    }

    // Extract username/email
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes(
                                        StandardCharsets.UTF_8
                                )
                        )
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


    // Extract role from ACCESS token
    public String extractRole(String token) {

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes(
                                        StandardCharsets.UTF_8
                                )
                        )
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }


    // Validate ACCESS token
    public boolean validateToken(
            String token,
            String username) {

        try {

            var claims = Jwts.parser()
                    .verifyWith(
                            Keys.hmacShaKeyFor(
                                    SECRET_KEY.getBytes(
                                            StandardCharsets.UTF_8
                                    )
                            )
                    )
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String type =
                    claims.get("type", String.class);

            String tokenUsername =
                    claims.getSubject();

            return "access".equals(type)
                    && username.equals(tokenUsername);

        } catch (JwtException | IllegalArgumentException e) {

            logger.warn(
                    "Invalid access token: {}",
                    e.getMessage()
            );

            return false;
        }
    }


    // Validate REFRESH token
    public boolean validateRefreshToken(
            String token) {

        try {

            var claims = Jwts.parser()
                    .verifyWith(
                            Keys.hmacShaKeyFor(
                                    SECRET_KEY.getBytes(
                                            StandardCharsets.UTF_8
                                    )
                            )
                    )
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String type =
                    claims.get("type", String.class);

            return "refresh".equals(type);

        } catch (JwtException | IllegalArgumentException e) {

            logger.warn(
                    "Invalid refresh token: {}",
                    e.getMessage()
            );

            return false;
        }
    }
}