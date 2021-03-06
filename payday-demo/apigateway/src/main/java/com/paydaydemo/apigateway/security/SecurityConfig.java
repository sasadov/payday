package com.paydaydemo.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private JwtAuthenticationProvider authProvider;
	
	@Autowired
    private UserNamePasswordAuthenticationProvider userNamePasswordAuthenticationProvider;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider).authenticationProvider(userNamePasswordAuthenticationProvider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {       
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .antMatchers("/apigateway/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/apigateway/signin").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/apigateway/accounts").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/apigateway/accounts").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/apigateway/transcations").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/notifications").hasRole("USER")
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer());
    }
}