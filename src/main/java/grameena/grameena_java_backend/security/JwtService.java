package grameena.grameena_java_backend.security;
import org.springframework.stereotype.Service;


import grameena.grameena_java_backend.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;
    private SecretKey getSignInKey(){

        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );

    }
    /**
     * Generate JWT Token
     */
    public String extractPhoneNumber(String token) {

     return extractClaim(token, Claims::getSubject);

     }
    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getPhoneNumber())          // Store phone number as subject
                .claim("userId", user.getUserId())       // Store userId as a custom claim
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    /**
     * Extract User ID from JWT
     */
    public Long extractUserId(String token) {

        Claims claims = extractAllClaims(token);

        return claims.get("userId", Long.class);

    }
    /**
     * Extract any claim
     */
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver) {

        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /**
     * Read all claims
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    /**
     * Check if token expired
     */
    public boolean isTokenExpired(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        ).before(new Date());

    }

    /**
     * Validate JWT
     */
    public boolean isTokenValid(String token) {

        return !isTokenExpired(token);

    }

}
