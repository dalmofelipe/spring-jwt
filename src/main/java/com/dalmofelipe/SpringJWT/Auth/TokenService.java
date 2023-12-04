package com.dalmofelipe.SpringJWT.Auth;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.dalmofelipe.SpringJWT.User.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {

    @Value("${jwt.token.expiration}")
    private String expirationTimeInMilis;

    @Value("${jwt.token.secret}")
    private String secret;

    private static final SecretKey KEY = Jwts.SIG.HS256.key().build();


    public String generateJWT(Authentication autheticate) {
        User userLogged = (User) autheticate.getPrincipal();
        
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + Long.parseLong(expirationTimeInMilis));
        
        String stringJWT = Jwts.builder()
            .issuer("spring-jwt-app")
            .subject(userLogged.getId().toString())
            .issuedAt(now)
            .expiration(expirationTime)
            .signWith(KEY)
            .claim("test", true)
            .compact();

        this.isTokenValid(stringJWT);

        return stringJWT;
    }


    public Boolean isTokenValid(String tokenWithoutBearer) {
        try {
            JwtParser jwtParser = Jwts.parser().verifyWith(KEY).build();
            jwtParser.parseSignedClaims(tokenWithoutBearer);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


    public Long getSubject(String tokenWithoutBearer) {
        JwtParser jwtParser = Jwts.parser().verifyWith(KEY).build();
        Claims payload = jwtParser.parseSignedClaims(tokenWithoutBearer).getPayload();
        String subject = payload.getSubject();
        return Long.parseLong(subject);
    }
    
}
