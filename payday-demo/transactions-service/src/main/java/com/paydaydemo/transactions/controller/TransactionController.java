package com.paydaydemo.transactions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paydaydemo.transactions.service.TransactionDto;
import com.paydaydemo.transactions.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}
	
	@GetMapping
	@Transactional(readOnly = true)
    public List<TransactionDto> GetTransactions(@RequestParam Long customerId)  {	
		return transactionService.getTransactions(customerId);
    }
}
