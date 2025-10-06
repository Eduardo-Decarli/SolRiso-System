package com.decarli.solriso_system.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.decarli.solriso_system.model.entities.UserEntity;
import com.decarli.solriso_system.model.exceptions.InvalidJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-in-minutes:15}")
    private int expirationInMinutes;

    @Value("${jwt.issuer}")
    private String issuer;

    public String generateToken(UserEntity userEntity) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Instant now = Instant.now();
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(userEntity.getEmail())
                    .withClaim("role", userEntity.getRole().name())
                    .withIssuedAt(Date.from(now))
                    .withExpiresAt(Date.from(now.plusSeconds(expirationInMinutes * 60)))
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error while generating token", ex);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new InvalidJwtException("Token inválido ou expirado");
        }
    }

//    public String generateResetPasswordToken(User user) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            Instant now = Instant.now();
//            return JWT.create()
//                    .withIssuer(issuer)
//                    .withSubject(user.getEmail())
//                    .withClaim("type", "RESET_PASSWORD")
//                    .withIssuedAt(Date.from(now))
//                    .withExpiresAt(Date.from(now.plusSeconds(expirationInMinutes * 60)))
//                    .sign(algorithm);
//        } catch (JWTCreationException ex) {
//            throw new RuntimeException("Erro ao gerar token de redefinição de senha", ex);
//        }
//    }

    public DecodedJWT getDecodedToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException ex) {
            throw new InvalidJwtException("Token inválido ou expirado");
        }
    }
}
