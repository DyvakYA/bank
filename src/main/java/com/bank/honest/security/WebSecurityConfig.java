package com.bank.honest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers("/*").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/users/*").hasRole("ADMIN")
//                .antMatchers("/register").permitAll()
//                .anyRequest().authenticated()
                .and()

                .exceptionHandling()
                .accessDeniedPage("/unauthorized")
                .and()

                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .and()

                //We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                //Add filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Create a default account
//        auth.inMemoryAuthentication()
//                .withUser("admin12")
//                .password("password")
//                .roles(String.valueOf(UserRole.ADMIN));
    }

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder();
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
