package com.kata.bank.account.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "account_id")
	private Long accountId;
	@Column(name = "account_number", unique = true, nullable = false)
	private Long accountNumber;
	@JsonIgnore
	@Column(name = "creation_date")
	private Date dateCreation;
	@JsonIgnore
	@Column(name = "account_balance")
	private double balance;
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "client_id")
	private Client client;
	
	
	public Account() {
		super();
	}

	public Account(Long accountNumber, Client client) {
		super();
		this.accountNumber = accountNumber;
		this.client = client;
		this.dateCreation = new Date();
		this.balance = 0d;	
	}
	
	public Account deposit(double operationAmount) {
		this.balance += operationAmount;
		return this;
	}
	
	public Account withdrawal(double operationAmount) {
		this.balance -= operationAmount;
		return this;
	}


	public Long getAccountId() {
		return accountId;
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


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
	
	
	
	
	

	




	
	
	

}
