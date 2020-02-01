package com.paydaydemo.accounts.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paydaydemo.accounts.model.Account;
import com.paydaydemo.accounts.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountsService {

	private AccountRepository accountRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
	
	@Override
	public AccountSummaryDto openAccount(AccountDto accountDto) {
		
		Account account = new Account();
		account.setAccountType(accountDto.accountType.toString());
		account.setBalance(new BigDecimal(0));
		account.setDate(new Date());
		account.setCustomerId(accountDto.CustomerId);
		account.setIsActive(true);
		account.setAccountNumber("1234567890");
		accountRepository.save(account);
		return new AccountSummaryDto(account);
	}

	@Override
	public List<AccountSummaryDto> getAccounts(Long customerId) {	
		return accountRepository.findByCustomerIdReturnStream(customerId).map(AccountSummaryDto::new).collect(Collectors.toList());
	}
	
//	private String generateAccountNumber()
//	{
//		
//	}
}
