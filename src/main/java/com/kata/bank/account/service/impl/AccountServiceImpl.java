package com.kata.bank.account.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.kata.bank.account.exception.AccountNotFoundException;
import com.kata.bank.account.exception.InsufficientFundException;
import com.kata.bank.account.exception.OperationTypeNotSupportedException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.model.TypeOperation;
import com.kata.bank.account.repository.AccountRepository;
import com.kata.bank.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static final Set<String> OPERATIONS_TYPES = Sets.newHashSet("WITHDRAWAL", "DEPOSIT");

	
	@Autowired
    private AccountRepository accountRepository;


	@Override
	public Optional<Account> getAccount(Long accountNumber) {
		return accountRepository.getAccountByAccountNumber(accountNumber);
	}
	
	@Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }
	
	@Override
	public Account getAccountWithNewBalance(Operation newOperation, Optional<Account> optionalAccount)
			throws InsufficientFundException, AccountNotFoundException, OperationTypeNotSupportedException {
		
		if ( !OPERATIONS_TYPES.contains(newOperation.getOperationType().toUpperCase()) ) {
			throw new OperationTypeNotSupportedException(newOperation.getOperationType());
		}
		
		if ( !optionalAccount.isPresent() ) {
			 throw new AccountNotFoundException(String.valueOf(newOperation.getAccount().getAccountNumber()));
		}
		
		Account account = optionalAccount.get();
		double operationAmount = newOperation.getAmount();
		
		if ( TypeOperation.WITHDRAWAL.toString().equalsIgnoreCase(newOperation.getOperationType()) ) {
			if ( operationAmount > account.getBalance() ) {
				throw new InsufficientFundException(String.valueOf(newOperation.getAccount().getAccountNumber()));
			}
			account = account.withdrawal(operationAmount);
			
		} 
		
		if ( TypeOperation.DEPOSIT.toString().equalsIgnoreCase(newOperation.getOperationType()) ) {
			account = account.deposit(operationAmount);
		}
		
		return account;
	}


}
