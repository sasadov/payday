package com.paydaydemo.accounts.service;

import java.math.BigDecimal;
import java.util.Date;

import com.paydaydemo.accounts.model.Account;

public class AccountSummaryDto {

	public String accountNumber;
	public AccountType accountType;
	public BigDecimal balance;
	public Date created;
	
	public AccountSummaryDto(Account account)
	{
		accountNumber = account.getAccountNumber();
		accountType = AccountType.valueOf(account.getAccountType());
		created = account.getDate();
		balance = account.getBalance();
	}
}
