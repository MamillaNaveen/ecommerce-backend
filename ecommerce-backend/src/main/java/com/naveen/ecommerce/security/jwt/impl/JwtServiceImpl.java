package com.naveen.ecommerce.security.jwt.impl;


import com.naveen.ecommerce.security.jwt.JwtProperties;
import com.naveen.ecommerce.security.jwt.JwtService;
import com.naveen.ecommerce.user.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    @Override
    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    @Override
    public boolean isTokenValid(String token, User user) {

        return extractUsername(token).equals(user.getEmail());
    }
}