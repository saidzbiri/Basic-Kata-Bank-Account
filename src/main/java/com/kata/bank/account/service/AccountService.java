package com.kata.bank.account.service;

import java.util.Optional;

import com.kata.bank.account.model.domain.Account;

public interface AccountService {

	public Account saveAccount(Account account);

	Optional<Account> findByAccountNumber(Long accountNumber);

}
