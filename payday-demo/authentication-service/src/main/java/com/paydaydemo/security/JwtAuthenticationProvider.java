package com.paydaydemo.security;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.paydaydemo.authentication.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	JwtUtility jwtUtility;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication==null) return null;
		JwtAuthentication jwtAuthentication = (JwtAuthentication)authentication;
		if (jwtUtility.validateToken(jwtAuthentication.getJwtToken())) {
			String username = jwtUtility.getUserName(jwtAuthentication.getJwtToken());
			String roles = jwtUtility.getRoles(jwtAuthentication.getJwtToken());
			Collection<? extends GrantedAuthority> authorities=  Arrays.stream(roles.split(";")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
			jwtAuthentication.setAuthorities(authorities);
			jwtAuthentication.setAuthenticated(true);
		}
		else {
			jwtAuthentication.setAuthenticated(false);
		}
		return jwtAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthentication.class.equals(authentication);
	}
}