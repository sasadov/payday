package com.paydaydemo.accounts.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@PostMapping("/open")
    public AccountSummaryDto OpenAccount(@RequestBody AccountDto account)  {

		AccountSummaryDto accountSummary = new AccountSummaryDto();
		accountSummary.accountNumber = "1234567890";
		accountSummary.accountType = account.accountType;
		accountSummary.created = new Date();
		accountSummary.balance = new BigDecimal(0);
		return accountSummary;
    }
}
