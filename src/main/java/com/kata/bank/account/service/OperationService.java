package com.kata.bank.account.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kata.bank.account.model.domain.Operation;
import com.kata.bank.account.model.dto.OperationCreationDto;



public interface OperationService {

	Page<Operation> findAllOperationsForAClient(Long accountNumber, Pageable pageable);

	Operation save(OperationCreationDto newOperation);
	

}
