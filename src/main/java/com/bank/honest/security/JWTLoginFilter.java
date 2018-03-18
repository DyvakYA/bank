package com.bank.honest.security;

import com.bank.honest.model.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by User on 3/18/2018.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    protected JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {


        System.out.println(request.getHeader("user") + "user from header");

        UserDTO user = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
        System.out.println(user.toString() + "user from input stream");

//        if (email != null && password != null) {
//            Optional<User> optionalUser = userService.login(email, password);
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//                logger.info(String.format(USER_LOGGED_IN, user.getEmail(), user.getId()));
//                result = Localization.getInstance()
//                        .getLocalizedMessage(request, LOGIN_USER_SUCCESSFUL_MSG) + user.getEmail();
//                request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, user);
//                destinationPage = getDestinationPageByUserRole(user, request);
//            }
//        }



        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                        user.getPhone(),
                        user.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException{
        TokenAuthentificationService.addAuthentication(response, auth.getName());
    }
}
