package com.kata.bank.account.service;

import java.util.Optional;

import com.kata.bank.account.exception.AccountNotFoundException;
import com.kata.bank.account.exception.InsufficientFundException;
import com.kata.bank.account.exception.OperationTypeNotSupportedException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;

public interface AccountService {
	
	Optional<Account> getAccount(Long accountNumber);
	
	public Account updateAccount(Account account);
    
	public Account getAccountWithNewBalance(Operation newOperation, Optional<Account> optionalAccount)
			throws InsufficientFundException, AccountNotFoundException, OperationTypeNotSupportedException;
	

}
