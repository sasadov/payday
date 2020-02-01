package com.paydaydemo.apigateway.service;

import java.util.Optional;

import com.paydaydemo.apigateway.exception.UserNotFoundException;
import com.paydaydemo.apigateway.model.User;

public interface UserService {
	
	public User getUserByUserName(String userName) throws UserNotFoundException;
	public Optional<User> findUserByUserName(String userName);
	public User createUser(User user);
}
