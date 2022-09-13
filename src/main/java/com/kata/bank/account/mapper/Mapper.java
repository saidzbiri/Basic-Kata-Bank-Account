package com.kata.bank.account.mapper;


import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.model.domain.Operation;
import com.kata.bank.account.model.dto.AccountDto;
import com.kata.bank.account.model.dto.OperationCreationDto;
import com.kata.bank.account.model.dto.OperationDto;

@Component
public class Mapper {

    public OperationDto toOperationDto(Operation operation) {
    	if(Objects.isNull(operation)){
            return null;
        }
        return OperationDto.builder()
        		.id(operation.getOperationId())
        		.operationType(operation.getOperationType())
        		.amount(operation.getAmount())
        		.operationDate(operation.getDate())
                .build();
    }
    
    
    public Operation toOperation(OperationCreationDto operationDto, Account account) {
    	return Operation.builder()
    					.operationType(operationDto.getOperationType())
    					.amount(operationDto.getAmount())
    					.date(new Date())
    					.account(account)
    					.build();
    }
    
	public AccountDto toAccountDto(Account account) {
		if(Objects.isNull(account)){
            return null;
        }
        return AccountDto.builder()
                .accountNumber(account.getAccountNumber())
                .dateCreation(account.getDateCreation())
                .balance(account.getBalance())
                .build();
	}
}
