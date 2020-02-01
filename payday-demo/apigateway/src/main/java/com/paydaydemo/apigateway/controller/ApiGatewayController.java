package com.paydaydemo.apigateway.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paydaydemo.apigateway.exception.SignUpFailedException;
import com.paydaydemo.apigateway.model.User;
import com.paydaydemo.apigateway.security.JwtAuthenticationProvider;
import com.paydaydemo.apigateway.security.JwtUtility;
import com.paydaydemo.apigateway.service.UserService;

@RestController
@RequestMapping("/authentication")
public class ApiGatewayController {
	
    private UserService userService;

    @Autowired
    JwtUtility jwtUtility;

	@Autowired
	public ApiGatewayController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest request) throws SignUpFailedException {

        try {
        	PasswordEncoder encoder =
        		     PasswordEncoderFactories.createDelegatingPasswordEncoder();           
         	String encoded = encoder.encode(request.user.getPassword());
        	request.user.setPassword(encoded);  
        	userService.createUser(request.user);
            return new ResponseEntity<User>(request.user, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SignUpFailedException();
        }
    }
	
	@PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signin() {

        try {
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	String token = jwtUtility.createToken(authentication.getName(), userService.findUserByUserName(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Username " + authentication.getName() + "not found")).getRoles());
            Map<String, String> model = new HashMap<>();
            model.put("username", authentication.getName());
            model.put("token", token);
            return new ResponseEntity<Map<String, String>>(model, HttpStatus.ACCEPTED);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
	
	@GetMapping("/test")
    public String test() {

       return "test success";
    }
}
