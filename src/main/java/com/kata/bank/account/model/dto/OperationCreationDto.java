package com.kata.bank.account.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OperationCreationDto {

	@NotNull(message = "accountNumber is mandatory")
	private Long accountNumber;
	@NotNull
	private String operationType;
	@Min(10)
	@Max(100_000)
	private double amount;

}
