package com.bank.honest.security;

import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationProvider authenticationProvider;

    @Autowired
    JWTAuthenticationEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JWTAuthenticationTokenFilter authenticationFilter(){
        JWTAuthenticationTokenFilter filter = new JWTAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JWTSuccessHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "*/users/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "*/users/*").authenticated()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

//                .antMatchers("/*").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/users/*").hasRole("ADMIN")
//                .antMatchers("/register").permitAll()
//                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);


                //We filter the api/login requests
                http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
                http.headers().cacheControl();
//
//
//
//
//                        new JWTLoginFilter("/login", authenticationManager()),
//                        UsernamePasswordAuthenticationFilter.class)
//
//                //Add filter other requests to check the presence of JWT in header
//                .addFilterBefore(new JWTAuthenticationTokenFilter(),
//                        UsernamePasswordAuthenticationFilter.class);
    }


//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                    .disable()
//                .authorizeRequests()
//                .antMatchers("/")
//                    .hasAnyRole("USER", "ADMIN")
//                .antMatchers("/admin/*")
//                    .hasRole("ADMIN")
//                .antMatchers("/register")
//                    .permitAll()
//                .and()
//        .exceptionHandling()
//                .accessDeniedPage("/unauthorized")
//                .and()
//        .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/j_spring_security_check")
//                .failureUrl("/login?error")
//                .usernameParameter("j_login")
//                .passwordParameter("j_password")
//                .permitAll()
//                .and()
//        .logout()
//                .permitAll()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")
//                .invalidateHttpSession(true);
//    }
}
