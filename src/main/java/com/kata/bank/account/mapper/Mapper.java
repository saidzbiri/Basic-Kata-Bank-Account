package com.kata.bank.account.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kata.bank.account.model.AccountDTO;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.model.OperationDTO;

@Component
public class Mapper {

    public OperationDTO toOperationDto(Operation operation) {
        return new OperationDTO(operation.getOperationType(), operation.getAmount(), operation.getDate());
    }
    
    public List<OperationDTO> toOperationDtoList(List<Operation> operations) {
    	
    	return operations
    			.stream()
    			.map(operation -> new OperationDTO(operation.getOperationType(), operation.getAmount(), operation.getDate()))
    			.collect(Collectors.toList());
    }

	public com.kata.bank.account.model.AccountDTO toAccountDTO(Account account) {
		return new AccountDTO(account.getAccountNumber(), account.getDateCreation(), account.getBalance());
	}
}
