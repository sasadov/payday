package com.paydaydemo.authentication.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paydaydemo.authentication.exception.SignUpFailedException;
import com.paydaydemo.authentication.model.User;
import com.paydaydemo.authentication.service.UserService;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	
    private UserService userService;


	@Autowired
	public AuthenticationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest request) throws SignUpFailedException {

        try {           
        	userService.CreateUser(request.user);
            return new ResponseEntity<User>(request.user, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SignUpFailedException();
        }
    }
	
	@PostMapping("/signin")
    public ResponseEntity<User> signin(@RequestBody SignInRequest request) {

//        try {
//            String username = request.getUsername();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
//            String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
//
//            Map<Object, Object> model = new HashMap<>();
//            model.put("username", username);
//            model.put("token", token);
//            return ok(model);
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid username/password supplied");
//        }
		return new ResponseEntity<User>(new User(Long.valueOf(1),"","", "", "", new Date(), "", ""), HttpStatus.ACCEPTED);
    }
}
