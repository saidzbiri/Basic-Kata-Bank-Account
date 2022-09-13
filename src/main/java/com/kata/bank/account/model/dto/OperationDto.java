package com.kata.bank.account.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OperationDto {

	private Long id;
	private String operationType;
	private double amount;
	private Date operationDate;
	
		
}
