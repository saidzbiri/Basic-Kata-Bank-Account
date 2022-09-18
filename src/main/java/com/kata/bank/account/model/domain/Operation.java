package com.kata.bank.account.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long operationId;

	@Column(name = "operation_type")
	private String operationType;

	private double amount;

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

}
