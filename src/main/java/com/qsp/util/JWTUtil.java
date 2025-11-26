package com.qsp.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
	
    private long expiryTime=60*60*1000;
    private String SECRET="Zp8+Hr3mC/YyXdJDS+wh1mZum+ujiKrXJf0n6t8Fk0o=";
    private SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public String generateToken(String subject) {
		String token = Jwts.builder()
		        .subject(subject)
		        .issuedAt(new Date())
		        .expiration(new Date(System.currentTimeMillis() + expiryTime))
		        .signWith(secretKey)    // No SignatureAlgorithm here
		        .compact();
		return token;
	}
}
