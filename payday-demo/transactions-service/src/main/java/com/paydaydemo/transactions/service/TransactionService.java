package com.paydaydemo.transactions.service;

import java.util.List;

public interface TransactionService {

	List<TransactionDto> getTransactions(Long customerId);
}
