package com.paydaydemo.apigateway.service;

import java.util.Date;

public class CustomerDto {
	
	public Long Id;
	public Long userId;
	public String firstName;
	public String lastName;
	public String gender;
	public String email;
	public String phoneNumber;
	public Date birthDate;

	public CustomerDto() {
		super();
	}
}
