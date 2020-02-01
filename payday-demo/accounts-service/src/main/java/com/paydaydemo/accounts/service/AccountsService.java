package com.paydaydemo.accounts.service;

import java.util.List;

public interface AccountsService {

	public AccountSummaryDto openAccount(AccountDto accountDto);
	public List<AccountSummaryDto> getAccounts(Long customerId);
}
