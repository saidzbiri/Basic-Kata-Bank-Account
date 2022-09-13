package com.kata.bank.account.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {

	private Long accountNumber;
	private Date dateCreation;
	private double balance;
	

}
