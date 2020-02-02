package com.paydaydemo.apigateway.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired        
    @LoadBalanced                        
    protected RestTemplate restTemplate; 

	@Value("${com.payday.apigateway.accountServiceUrl}")
	protected String serviceUrl;

	@Override
	public List<AccountSummaryDto> getAccountSummary(Long customerId) {
		ParameterizedTypeReference<List<AccountSummaryDto>> parameterizedEntity =
			     new ParameterizedTypeReference<List<AccountSummaryDto>>() {};
		ResponseEntity<List<AccountSummaryDto>> response =  restTemplate.exchange(serviceUrl+
				"/accounts?customerId={customerId}", HttpMethod.GET, null, parameterizedEntity, customerId);
		return response.getBody();
	}

	@Override
	public Optional<AccountSummaryDto> openAccount(AccountDto account) {
		
		ResponseEntity<AccountSummaryDto> response = restTemplate.postForEntity(serviceUrl+ 
				"/accounts", account, AccountSummaryDto.class);
		if (response.getStatusCode()==HttpStatus.OK) {
			return Optional.of(response.getBody());
		}
		return Optional.<AccountSummaryDto>empty();
	}
}
