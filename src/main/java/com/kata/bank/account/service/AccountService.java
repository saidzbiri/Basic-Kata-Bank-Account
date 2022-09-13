package com.kata.bank.account.service;


import com.kata.bank.account.model.domain.Account;

public interface AccountService {
	
		
	public Account saveAccount(Account account);
    	
	Account findByAccountNumber(Long accountNumber);


}
