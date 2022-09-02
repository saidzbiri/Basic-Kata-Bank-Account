package com.kata.bank.account.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bank.account.exception.AccountNotFoundException;
import com.kata.bank.account.exception.InsufficientFundException;
import com.kata.bank.account.exception.OperationTypeNotSupportedException;
import com.kata.bank.account.mapper.Mapper;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.model.OperationDTO;
import com.kata.bank.account.service.AccountService;
import com.kata.bank.account.service.OperationService;

@RestController
@RequestMapping("/operations")
public class OperationController {
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private Mapper mapper;
	
	
	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Operation> executeOperation(@RequestBody Operation newOperation) throws InsufficientFundException, AccountNotFoundException, OperationTypeNotSupportedException {
		
		Optional<Account> optionalAccount = accountService.getAccount(newOperation.getAccount().getAccountNumber());		
		Account account = accountService.getAccountWithNewBalance(newOperation, optionalAccount);
	
		accountService.updateAccount(account);
		newOperation.setAccount(account);
		newOperation.setDate(new Date());
		
		Operation operation = operationService.registerOperation(newOperation);	
		return new ResponseEntity<>(operation, HttpStatus.CREATED);
	
	}

    @GetMapping
    public ResponseEntity<List<OperationDTO>> getAllOperationForAClient(
                                                    @RequestParam(value = "accountNumber", required = true) Long accountNumber,
                                                    @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                    @RequestParam(value = "size", required = false, defaultValue = "10") int size
    												) throws AccountNotFoundException {


        Optional<Account> optionalAccount = accountService.getAccount(accountNumber);
        if (! optionalAccount.isPresent()) {
            throw new AccountNotFoundException(String.valueOf(accountNumber));
        }
        Account account = optionalAccount.get();

        List<Operation> operationHistory = operationService.getAllOperationForAccount(account, page, size);

        List<OperationDTO> operations = mapper.toOperationDtoList(operationHistory);
        return ResponseEntity.status(HttpStatus.OK).body(operations);
    }
	

	
	

}
