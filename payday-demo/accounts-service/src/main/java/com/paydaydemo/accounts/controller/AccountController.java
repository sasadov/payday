package com.paydaydemo.accounts.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paydaydemo.accounts.model.Account;
import com.paydaydemo.accounts.service.AccountDto;
import com.paydaydemo.accounts.service.AccountSummaryDto;
import com.paydaydemo.accounts.service.AccountsService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountsService accountsService;
	
	@Autowired
	public AccountController(AccountsService accountsService) {
		super();
		this.accountsService = accountsService;
	}
	
	@PostMapping
    public AccountSummaryDto OpenAccount(@RequestBody AccountDto account)  {
	
		account.CustomerId = 1001L;
		AccountSummaryDto accountSummary = accountsService.openAccount(account);
		return accountSummary;
    }
	
	@GetMapping
	@Transactional(readOnly = true)
    public List<AccountSummaryDto> GetAccounts()  {
		
		Long customerId = 1001L;
		return accountsService.getAccounts(customerId);
    }
}
