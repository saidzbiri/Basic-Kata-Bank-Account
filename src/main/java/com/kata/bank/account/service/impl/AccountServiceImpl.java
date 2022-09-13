package com.kata.bank.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.bank.account.exception.AccountNotFoundException;
import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.repository.AccountRepository;
import com.kata.bank.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
		
	@Autowired
    private AccountRepository accountRepository;

	
	@Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

	@Override
	public Account findByAccountNumber(Long accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException(String.valueOf(accountNumber)));
	}




}
