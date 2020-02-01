package com.paydaydemo.accounts.model;

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
@Table(name = "PDAY_ACCOUNTS", schema = "PD")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACCOUNT_ID")
    @SequenceGenerator(sequenceName = "SQ_ACCOUNT_ID", schema = "PD", allocationSize = 1, name = "SQ_ACCOUNT_ID")
    Long id;
	
	@Column(name = "CUSTOMER_ID")
	Long CustomerId;
	
	@Column(name = "ACCOUNT_NUMBER")
	String AccountNumber;

	@Column(name = "ACCOUNT_TYPE")
	String AccountType;

	@Column(name = "IS_ACTIVE")
	Boolean IsActive;
	
	@Column(name = "BALANCE")
	BigDecimal Balance;

    @Column(name = "CREATED_DATE")
    Date date;
    	
	public Account() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}

	public BigDecimal getBalance() {
		return Balance;
	}

	public void setBalance(BigDecimal balance) {
		Balance = balance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
