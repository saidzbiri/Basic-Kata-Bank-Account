package com.kata.bank.account.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.kata.bank.account.exception.AccountNotFoundException;
import com.kata.bank.account.exception.InsufficientFundException;
import com.kata.bank.account.exception.OperationTypeNotSupportedException;
import com.kata.bank.account.mapper.Mapper;
import com.kata.bank.account.model.TypeOperation;
import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.model.domain.Operation;
import com.kata.bank.account.model.dto.OperationCreationDto;
import com.kata.bank.account.repository.OperationRepository;
import com.kata.bank.account.service.AccountService;
import com.kata.bank.account.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	private static final Set<String> OPERATIONS_TYPES = Sets.newHashSet("WITHDRAWAL", "DEPOSIT");

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private AccountService accountService;

	@Autowired
	private Mapper mapper;

	@Override
	public Page<Operation> findAllOperationsByAccount(Long accountNumber, Pageable pageable) {
		return operationRepository.findByAccount_AccountNumber(accountNumber, pageable);
	}

	@Override
	public Operation executeOperation(OperationCreationDto operationRequest) {
		Long accountNumber = operationRequest.getAccountNumber();
		double operationAmount = operationRequest.getAmount();
		String operationType = operationRequest.getOperationType();

		Account account = accountService.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException(String.valueOf(accountNumber)));

		if (!OPERATIONS_TYPES.contains(operationType.toUpperCase())) {
			throw new OperationTypeNotSupportedException(operationType);
		}

		if (TypeOperation.WITHDRAWAL.name().equalsIgnoreCase(operationType)) {
			if (operationAmount > account.getBalance()) {
				throw new InsufficientFundException(String.valueOf(accountNumber));
			}
			account.setBalance(account.getBalance() - operationAmount);

		}

		if (TypeOperation.DEPOSIT.name().equalsIgnoreCase(operationType)) {
			account.setBalance(account.getBalance() + operationAmount);
		}

		Operation operation = mapper.toOperation(operationRequest, account);
		accountService.saveAccount(account);

		return operationRepository.save(operation);

	}

}
