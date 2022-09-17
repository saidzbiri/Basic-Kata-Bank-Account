package com.kata.bank.account.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bank.account.mapper.Mapper;
import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.model.dto.AccountDto;
import com.kata.bank.account.service.AccountService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private Mapper mapper;
	
	@ApiOperation("returns details of an account")
	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountDto> printAccountStatement(@PathVariable Long accountNumber) {		
		Optional<Account> optionalAccount = accountService.findByAccountNumber(accountNumber);
		
		return optionalAccount.map(account -> ResponseEntity.ok().body(mapper.toAccountDto(account))).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
