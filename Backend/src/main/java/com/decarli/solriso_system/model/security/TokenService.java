package com.decarli.solriso_system.model.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.decarli.solriso_system.model.entities.Admin;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String key = "myPasswordForSystemOfReservation";

    public String generateToken(Admin admin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            String token = JWT.create()
                    .withIssuer("reservation-system")
                    .withSubject(admin.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        } catch(JWTCreationException ex) {
            throw new RuntimeException("Error while generation token", ex);
        }
    }

    public String validateToke(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT.require(algorithm)
                    .withIssuer("reservation-system")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException ex) {
            return "Verification token error";
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
