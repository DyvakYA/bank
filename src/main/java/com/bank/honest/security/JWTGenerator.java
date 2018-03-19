package com.bank.honest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by User on 3/19/2018.
 */
public class JWTGenerator {

    public String generate(JWTUser jwtUser) {

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getPhone());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole().toString());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
}
