package com.kata.bank.account.exception;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(String accountNumber) {
        super(String.format("Account with number %s has not been found.", accountNumber));
    }
	
}
