package com.paydaydemo.accounts.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountDto {

	@JsonIgnore
	public Long CustomerId;
	public AccountType accountType;
}
