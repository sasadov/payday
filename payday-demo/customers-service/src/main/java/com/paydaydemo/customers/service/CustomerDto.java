package com.paydaydemo.customers.service;

import java.util.Date;

import com.paydaydemo.customers.model.Customer;

public class CustomerDto {
	
	public Long Id;
	public Long userId;
	public String firstName;
	public String lastName;
	public String gender;
	public String email;
	public String phoneNumber;
	public Date birthDate;
	
	public CustomerDto(Customer customer) {
		this.Id = customer.getId();
		this.userId = customer.getUserId();
		this.birthDate = customer.getBirthDate();
		this.email = customer.getEmail();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.gender = customer.getGender();
		this.phoneNumber = customer.getPhoneNumber();
	}

	public CustomerDto() {
		super();
	}
}
