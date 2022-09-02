package com.kata.bank.account.model;

import java.util.Date;


public class OperationDTO {

	
	private String operationType;
	private double amount;
	private Date operationDate;
	
	public OperationDTO(String operationType, double amount, Date operationDate) {
		super();
		this.operationType = operationType;
		this.amount = amount;
		this.operationDate = operationDate;
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

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	
	
	
	
}
