package com.paydaydemo.apigateway.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paydaydemo.apigateway.exception.UserNotFoundException;
import com.paydaydemo.apigateway.model.User;
import com.paydaydemo.apigateway.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public User createUser(User user) {
		
		userRepository.save(user);
		return user;
	}

	@Override
	public User getUserByUserName(String userName) throws UserNotFoundException{
		Optional<User> optionalUser = userRepository.findUserByUserName(userName);
		if (optionalUser.isPresent()) return optionalUser.get();
		throw new UserNotFoundException();
	}

	@Override
	public Optional<User> findUserByUserName(String userName) {

		return userRepository.findUserByUserName(userName);
	}
}
