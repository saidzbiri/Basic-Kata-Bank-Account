package com.kata.bank.account.exception;

public class InsufficientFundException extends IllegalStateException {

	  public InsufficientFundException(String accountNumber) {
	        super(String.format("operation refused, insufficient fund for account %s.", accountNumber));
	  }
	  
}
