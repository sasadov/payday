package com.paydaydemo.apigateway.security;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
        throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) req);
        if (token != null) {
            Authentication authentication = new JwtAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else {
        	UserNamePassword userdata = getUserdata((HttpServletRequest) req);
            if (userdata != null) {
                Authentication authentication = new UserNamePasswordAuthentication(userdata.userName, userdata.password);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
		}
        filterChain.doFilter(req, res);
    }
    
    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    
    private UserNamePassword getUserdata(HttpServletRequest req) {
		String basic = req.getHeader("Authorization");  
		if (basic == null || !basic.startsWith("Basic ")) return null;
		String userpassEncoded = basic.substring(6);  
		byte[] decodedBytes = Base64.getDecoder().decode(userpassEncoded);  
		String userpassDecoded = new String(decodedBytes);  	      
		String account[] = userpassDecoded.split(":");
		UserNamePassword userNamePassword  = new UserNamePassword();
		userNamePassword.userName = account[0];
		userNamePassword.password = account[1];
		return userNamePassword;
    }
    
    static class UserNamePassword
    {    	
    	public String userName;
    	public String password;
    }
}

