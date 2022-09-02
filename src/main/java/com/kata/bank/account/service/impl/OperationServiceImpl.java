package com.kata.bank.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.repository.OperationRepository;
import com.kata.bank.account.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {
	
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Operation registerOperation(Operation newOperation) {
		return operationRepository.save(newOperation);
	}

	
	@Override
	public List<Operation> getAllOperationForAccount(Account account, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"date"));
        return operationRepository.findByAccount(account, pageRequest);
	}

}
