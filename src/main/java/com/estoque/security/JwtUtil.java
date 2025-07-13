package com.estoque.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a secure key
    private final long EXPIRATION = 86400000; // 1 day

    public String generateToken(org.springframework.security.core.userdetails.User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) // 1 day expiration
                .signWith(SECRET_KEY) // Use the Key object for signing
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY)
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername()) &&
                !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY)
                .build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}