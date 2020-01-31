package com.paydaydemo.authentication.service;

import com.paydaydemo.authentication.exception.UserNotFoundException;
import com.paydaydemo.authentication.model.User;

public interface UserService {
	
	public User getUserByUserName(String userName) throws UserNotFoundException;
	public User CreateUser(User user);
}
