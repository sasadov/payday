package com.paydaydemo.apigateway.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired        
    @LoadBalanced                        
    protected RestTemplate restTemplate; 

	@Value("${com.payday.apigateway.customerServiceUrl}")
	protected String serviceUrl;

	@Override
	public Optional<CustomerDto> getCustomerByUserId(Long userId) {
		CustomerDto customer = restTemplate.getForObject(serviceUrl
                + "/customers?userId={userId}", CustomerDto.class, userId);
		if (customer==null) return Optional.<CustomerDto>empty();
       return Optional.of(customer);
	}

	@Override
	public Optional<CustomerDto> createCustomer(CustomerDto customer) {
		
		ResponseEntity<CustomerDto> response = restTemplate.postForEntity(serviceUrl+ 
				"/customers", customer, CustomerDto.class);
		if (response.getStatusCode()==HttpStatus.CREATED) {
			return Optional.of(response.getBody());
		}
		return Optional.<CustomerDto>empty();
	}
}
