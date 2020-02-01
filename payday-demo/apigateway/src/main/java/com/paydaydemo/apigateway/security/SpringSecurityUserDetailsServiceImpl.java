package com.paydaydemo.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paydaydemo.apigateway.repository.UserRepository;

@Service
public class SpringSecurityUserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public SpringSecurityUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
}
