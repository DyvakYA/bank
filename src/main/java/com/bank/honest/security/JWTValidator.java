package com.bank.honest.security;

import com.bank.honest.model.entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

/**
 * Created by User on 3/19/2018.
 */
@Component
public class JWTValidator {

    static final String SECRET_KEY = "youtube";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";


    public JWTUser validate(String token) {

        JWTUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = JWTUser.builder()
                    .phone(body.getSubject())
                    .id(Long.parseLong((String) body.get("userId")))
                    .role(UserRole.valueOf((String) body.get("role")))
                    .build();

        } catch (Exception e) {
            System.out.println(e);
        }
        return jwtUser;
    }
}
