package com.paydaydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication != null) {		
			UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
			PasswordEncoder encoder =
       		     PasswordEncoderFactories.createDelegatingPasswordEncoder();    
        	if (encoder.matches((String)authentication.getCredentials(), userDetails.getPassword())) {
        		Authentication validatedAuthentication = new UserNamePasswordAuthentication((String)authentication.getPrincipal(), (String)authentication.getCredentials() , userDetails.getAuthorities());
        		validatedAuthentication.setAuthenticated(true);
        		return validatedAuthentication;
			} else {
				authentication.setAuthenticated(false);
			}
        	return authentication;
			
		} else {
			return null;
		}
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return UserNamePasswordAuthentication.class.equals(authentication);
	}
}
