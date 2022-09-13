package com.kata.bank.account.exception;

public class OperationTypeNotSupportedException extends RuntimeException {

	public OperationTypeNotSupportedException(String operationType) {
		super(String.format("Operation type '%s' is not supported.", operationType));
	}
}
