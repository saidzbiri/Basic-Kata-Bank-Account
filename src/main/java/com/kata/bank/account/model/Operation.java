package com.kata.bank.account.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long operationId;
	@Column(name = "operation_type")
	private String operationType;
	private double amount;
	@JsonIgnore
	@Column(name = "operation_date")
	private Date date;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
	private Account account;
	
	
	public Operation() {
		super();
	}


	public Operation(String operationType, double amount, Account account) {
		super();
		this.operationType = operationType;
		this.amount = amount;
		this.account = account;
		this.date = new Date();
	}


	public Long getOperationId() {
		return operationId;
	}


	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}


	public String getOperationType() {
		return operationType;
	}


	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	

}
