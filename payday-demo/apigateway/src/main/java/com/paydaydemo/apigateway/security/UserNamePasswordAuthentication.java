package com.paydaydemo.apigateway.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UserNamePasswordAuthentication implements Authentication{

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean isAuthenticated;
	
	public UserNamePasswordAuthentication(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public UserNamePasswordAuthentication(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated; 	
	}

}
