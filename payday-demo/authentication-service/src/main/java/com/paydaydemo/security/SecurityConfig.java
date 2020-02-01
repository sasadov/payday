package com.paydaydemo.security;

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
                .antMatchers("/authentication/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/authentication/signin").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/authentication/test").hasRole("ADMIN")
                .antMatchers("/accounts/open").permitAll()
                .antMatchers(HttpMethod.GET, "/transcations/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/accounts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/notifications/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer());
    }
}