package com.kata.bank.account.service;

import java.util.List;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;



public interface OperationService {
	
	Operation registerOperation(Operation newOperationRequest);
	
	List<Operation> getAllOperationForAccount(Account account, int page, int size);
	

}
