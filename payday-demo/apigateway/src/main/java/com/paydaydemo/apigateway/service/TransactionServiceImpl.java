package com.paydaydemo.apigateway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired        
    @LoadBalanced                        
    protected RestTemplate restTemplate; 

	@Value("${com.payday.apigateway.transactionsServiceUrl}")
	protected String serviceUrl;

	@Override
	public List<TransactionDto> getTransactions(Long customerId) {
		ParameterizedTypeReference<List<TransactionDto>> parameterizedEntity =
			     new ParameterizedTypeReference<List<TransactionDto>>() {};
		ResponseEntity<List<TransactionDto>> response =  restTemplate.exchange(serviceUrl+
				"/transactions?customerId={customerId}", HttpMethod.GET, null, parameterizedEntity, customerId);
		return response.getBody();
	}
}
