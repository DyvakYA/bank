package com.bank.honest.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by User on 3/19/2018.
 */
public class JWTAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String token;

    public JWTAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
