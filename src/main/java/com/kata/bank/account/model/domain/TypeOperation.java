package com.kata.bank.account.model.domain;

public enum TypeOperation {

	DEPOSIT("DEPOSIT"),
	WITHDRAWAL("WITHDRAWAL");

	private String type = "";

	TypeOperation(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
