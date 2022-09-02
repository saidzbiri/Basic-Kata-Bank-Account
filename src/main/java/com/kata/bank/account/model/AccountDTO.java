package com.kata.bank.account.model;

import java.util.Date;

public class AccountDTO {

	private Long accountNumber;
	private Date dateCreation;
	private double balance;
	
	public AccountDTO(Long accountNumber, Date dateCreation, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.dateCreation = dateCreation;
		this.balance = balance;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	


}
