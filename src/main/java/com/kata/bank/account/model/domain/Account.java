package com.kata.bank.account.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "account_number", unique = true, nullable = false)
	private Long accountNumber;

	@Column(name = "creation_date")
	private Date dateCreation;

	@Column(name = "account_balance")
	private double balance;

	@OneToOne
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
	}

	public Account(Long accountNumber, double balance, Client client) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.client = client;
		this.dateCreation = new Date();
	}

}
