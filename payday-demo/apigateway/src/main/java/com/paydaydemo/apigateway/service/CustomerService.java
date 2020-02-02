package com.paydaydemo.apigateway.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

public interface CustomerService {

	public Optional<CustomerDto> getCustomerByUserId(Long userId);
	public Optional<CustomerDto> createCustomer(CustomerDto customer);
}
