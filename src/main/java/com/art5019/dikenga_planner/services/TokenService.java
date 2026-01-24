package com.art5019.dikenga_planner.services;

import com.art5019.dikenga_planner.exceptions.TokenGenerationException;
import com.art5019.dikenga_planner.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("$api.security.token.secret")
    private String secret;

    public String generateToken(User u) {
        try {
            Algorithm algo = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("Art5019").withSubject(u.getEmail()).withExpiresAt(generateExpiry()).sign(algo);
        } catch (Exception e) {
            throw new TokenGenerationException("Error generating token user!");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo).withIssuer("Art5019").build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant generateExpiry() {
        return LocalDateTime.now().plusDays(14).toInstant(ZoneOffset.of("+0"));
    }
}

