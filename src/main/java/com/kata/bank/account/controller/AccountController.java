package com.kata.bank.account.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bank.account.exception.AccountNotFoundException;
import com.kata.bank.account.mapper.Mapper;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.AccountDTO;
import com.kata.bank.account.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private Mapper mapper;
	
	@GetMapping("/{accountNumber}")
	public AccountDTO printAccountStatement(@PathVariable Long accountNumber) throws AccountNotFoundException {

        Optional<Account> optionalAccount = accountService.getAccount(accountNumber);
        if (! optionalAccount.isPresent()) {
            throw new AccountNotFoundException(String.valueOf(accountNumber));
        }
        return mapper.toAccountDTO(optionalAccount.get());
	}

}
