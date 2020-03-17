package com.paydaydemo.transactions.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PDAY_TRANSACTIONS", schema = "PD")
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final long unusedFieldThatCanBeDeleted = 0L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TRANSACTION_ID")
    @SequenceGenerator(sequenceName = "SQ_TRANSACTION_ID", schema = "PD", allocationSize = 1, name = "SQ_TRANSACTION_ID")
    Long id;
	
	@Column(name = "CUSTOMER_ID")
	Long customerId;
	
	@Column(name = "ACCOUNT_NUMBER")
	String accountNumber;
	
	@Column(name = "AMOUNT")
	BigDecimal amount;
	
	@Column(name = "DESCRIPTION")
	String description;

    @Column(name = "CREATED_DATE")
    Date date;
    	
	public Transaction() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	
}
