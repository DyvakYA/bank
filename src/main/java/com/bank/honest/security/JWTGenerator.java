package com.bank.honest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by User on 3/19/2018.
 */
@Component
public class JWTGenerator {

    static final long EXPIRATION_TIME = 600_000; //10 min
    static final String SECRET_KEY = "youtube";

    public String generate(JWTUser jwtUser) {

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getPhone());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole().toString());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
