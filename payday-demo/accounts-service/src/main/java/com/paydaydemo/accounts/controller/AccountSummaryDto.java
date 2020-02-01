package com.paydaydemo.accounts.controller;

import java.math.BigDecimal;
import java.util.Date;

public class AccountSummaryDto {

	public String accountNumber;
	public AccountType accountType;
	public BigDecimal balance;
	public Date created;
}
