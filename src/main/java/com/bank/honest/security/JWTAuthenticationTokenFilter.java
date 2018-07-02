package com.bank.honest.security;

import com.bank.honest.exception.TokenIsMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by User on 3/18/2018.
 */
public class JWTAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JWTValidator validator;


    protected JWTAuthenticationTokenFilter() {
        super("/users/*");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.print("Header Name:" + headerName);
            String headerValue = request.getHeader(headerName);
            System.out.println("Header Value:" + headerValue);
            System.out.println("*****");
        }


        String header = request.getHeader("x-authorization");
        System.out.println("**************************************************");
        System.out.println("Header: " + header);


        if (header == null || !header.startsWith("Token ")) {
            throw new TokenIsMissingException("Token is missing");
        }

        String authenticationToken = header.substring(6);
        JWTAuthenticationToken token = new JWTAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
