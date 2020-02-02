package com.paydaydemo.apigateway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface AccountService {

	public Optional<AccountSummaryDto> openAccount(AccountDto account);
	public List<AccountSummaryDto> getAccountSummary(Long customerId);
}
