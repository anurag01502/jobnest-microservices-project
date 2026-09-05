package com.job.security;


import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private byte[] getSecretKey() {
        return SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    }

    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(getSecretKey()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String extractRole(String token) {

        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(getSecretKey()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    public boolean validateToken(String token, String username) {

        String tokenUsername = extractUsername(token);

        return tokenUsername.equals(username);
    }
}