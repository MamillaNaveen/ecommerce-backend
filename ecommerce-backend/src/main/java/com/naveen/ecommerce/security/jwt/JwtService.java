package com.naveen.ecommerce.security.jwt;

import com.naveen.ecommerce.user.entity.User;

public interface JwtService {

    String generateToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, User user);
}