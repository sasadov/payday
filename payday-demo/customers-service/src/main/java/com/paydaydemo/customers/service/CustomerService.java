package com.paydaydemo.customers.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

	public Optional<CustomerDto> getCustomerByUserId(Long userId);
	public CustomerDto createCustomer(CustomerDto customer);
	
}
