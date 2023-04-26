package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.security;


import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
    private static final String ACCESS_TOKEN_SECRET = "mTmaw5XSpJmVckRQHKO4esWjQ_QFmOoDJAHK1mrN5kA";
    private static final Long ACCESS_TOKEN_VALID_SECONDS = 2_592_000L;

    private TokenUtils() {}

    public static String createToken(String name, String email) {
        long expirationTime = ACCESS_TOKEN_VALID_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);

        return Jwts.builder()
        .setSubject(email)
        .setExpiration(expirationDate)
        .addClaims(extra)
        .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
        .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
            .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException jwtException) {
            return null;
        }
    }
}
