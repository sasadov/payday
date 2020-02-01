package com.paydaydemo.transactions.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paydaydemo.transactions.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	private TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}

	@Override
	public List<TransactionDto> getTransactions(Long customerId) {
		return transactionRepository.findByCustomerIdReturnStream(customerId).map(TransactionDto::new).collect(Collectors.toList());
	}

}
