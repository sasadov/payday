package com.paydaydemo.customers.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paydaydemo.customers.model.Customer;
import com.paydaydemo.customers.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public Optional<CustomerDto> getCustomerByUserId(Long userId) {
		Optional<Customer> customer = customerRepository.findByUserId(userId);	
		if (customer.isPresent()) {
			CustomerDto customerDto = new CustomerDto(customer.get());
			return Optional.of(customerDto);
		}
		return Optional.<CustomerDto>empty();
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customer) {

		Customer customerEntity = new Customer();
		customerEntity.setUserId(customer.userId);
		customerEntity.setBirthDate(customer.birthDate);
		customerEntity.setDate(new Date());
		customerEntity.setEmail(customer.email);
		customerEntity.setFirstName(customer.firstName);
		customerEntity.setGender(customer.gender);
		customerEntity.setLastName(customer.lastName);
		customerEntity.setPhoneNumber(customer.phoneNumber);
		customerRepository.save(customerEntity);
		
		customer.Id = customerEntity.getId();
		return customer;
	}
}
