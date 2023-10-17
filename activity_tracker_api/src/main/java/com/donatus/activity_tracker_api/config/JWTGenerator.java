package com.donatus.activity_tracker_api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTGenerator {

//    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//    public String generateToken(Authentication authentication){
//        String email = authentication.getName();
//        Date currentDate = new Date();
//        Date expirationDate = new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRATION);
//
//        return Jwts.builder().setSubject(email)
//                .setExpiration(expirationDate)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    public String getEmailFromJWT(String token){
//        Claims claims = Jwts.parser()
//                .setSigningKey(key)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.getSubject();
//    }
//
//    public boolean validateToken(String token){
//        try {
//            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
//
//            return true;
//        }catch (Exception e){
//            throw new AuthenticationCredentialsNotFoundException("JWT has expired or incorrect!");
//        }
//    }

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + 1000 * 60 * 24);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt( new Date())
                .setExpiration(expireDate)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
    }
    public String getEmailFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
        }
    }
}
