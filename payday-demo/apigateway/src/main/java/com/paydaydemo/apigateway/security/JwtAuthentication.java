package com.paydaydemo.apigateway.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthentication implements Authentication{

	private static final long serialVersionUID = 1L;
	private String jwtToken;
	private boolean isAuthenticated;
	private String username;
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public JwtAuthentication(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}
		
	public String getJwtToken() {
		return jwtToken;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated= isAuthenticated;
	}
	
	
}
