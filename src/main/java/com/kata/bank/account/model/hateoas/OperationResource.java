package com.kata.bank.account.model.hateoas;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.kata.bank.account.model.dto.OperationDto;


import lombok.Getter;

@Getter
public class OperationResource extends ResourceSupport {

	private Long operationId;
	private String operationType;
	private double amount;
	private Date operationDate;
	
    public OperationResource(OperationDto operation) {
        this.operationId = operation.getId();
        this.operationType = operation.getOperationType();
        this.amount = operation.getAmount();
        this.operationDate = operation.getOperationDate();
    }
}
