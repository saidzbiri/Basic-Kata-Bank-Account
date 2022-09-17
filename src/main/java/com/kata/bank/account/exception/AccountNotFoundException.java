package com.kata.bank.account.exception;

import org.springframework.web.client.ResourceAccessException;

public class AccountNotFoundException extends ResourceAccessException {

	public AccountNotFoundException(String accountNumber) {
        super(String.format("Account with number %s has not been found.", accountNumber));
    }
	
}
