package com.paydaydemo.transactions.service;

import java.math.BigDecimal;
import java.util.Date;

import com.paydaydemo.transactions.model.Transaction;

public class TransactionDto {

	public String accountNumber;	
	public BigDecimal amount;
	public String description;
	public Date date;
	public String feature1;
	public String feature2;
    
	public TransactionDto(Transaction transaction) {
		super();
		this.accountNumber = transaction.getAccountNumber();
		this.amount = transaction.getAmount();
		this.description = transaction.getDescription();
		this.date = transaction.getDate();
	}       
}
