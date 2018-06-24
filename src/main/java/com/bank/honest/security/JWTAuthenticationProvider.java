package com.bank.honest.security;

import com.bank.honest.exception.TokenIsIncorrectException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by User on 3/19/2018.
 */
@Log4j
@Component
public class JWTAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JWTValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken token)
            throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String userName,
                                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
            throws AuthenticationException {

        JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) usernamePasswordAuthenticationToken;

        String token = jwtAuthenticationToken.getToken();
        log.debug(token);
        JWTUser jwtUser = validator.validate(token);
        log.debug(jwtUser);

        if (jwtUser == null) {
            throw new TokenIsIncorrectException("JWT Token is incorrect");
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser.getRole().toString());
        return new JWTUserDetails(jwtUser.getPhone(),
                jwtUser.getId(),
                token,
                grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (JWTAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
