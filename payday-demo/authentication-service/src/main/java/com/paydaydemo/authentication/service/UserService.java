package com.paydaydemo.authentication.service;

import java.util.Optional;

import com.paydaydemo.authentication.exception.UserNotFoundException;
import com.paydaydemo.authentication.model.User;

public interface UserService {
	
	public User getUserByUserName(String userName) throws UserNotFoundException;
	public Optional<User> findUserByUserName(String userName);
	public User CreateUser(User user);
}
