package com.edgarfrancisco.HyperbolicTimeChamber.security;

import com.edgarfrancisco.HyperbolicTimeChamber.security.filter.JwtAccessDeniedHandler;
import com.edgarfrancisco.HyperbolicTimeChamber.security.filter.JwtAuthenticationEntryPoint;
import com.edgarfrancisco.HyperbolicTimeChamber.security.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.edgarfrancisco.HyperbolicTimeChamber.constant.SecurityConstant.PUBLIC_URLS;
import static com.edgarfrancisco.HyperbolicTimeChamber.constant.SecurityConstant.PUBLIC_URLS;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/*
    All security related source code based on the Udemy course
    JSON Web Token (JWT) with Spring Security And Angular
    https://www.udemy.com/course/jwt-springsecurity-angular/
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Spring Security pre/post annotations.
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS) //no HTTP session for storing the authentication object
                .and().authorizeRequests().antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler) // user does not have permission
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // user is not logged in
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class); // JWT
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
