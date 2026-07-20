package com.vitig.car_rent.data.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    private String getSecretKey() {
        return secretKey;
    }

    private SecretKey getSecretKeyAsBytes() {
        return Keys.hmacShaKeyFor(getSecretKey().getBytes());
    }

    public String generateToken(String username) {
        Date now = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + 86400000);
        return Jwts.builder().subject(username).issuedAt(now).expiration(expiration).signWith(getSecretKeyAsBytes()).compact();
    }

    private Claims getClaims(String token){
        return Jwts.parser().verifyWith(getSecretKeyAsBytes()).build().parseSignedClaims(token).getPayload();
    }

    public String getUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isValid(String token){
        try{
            getClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
