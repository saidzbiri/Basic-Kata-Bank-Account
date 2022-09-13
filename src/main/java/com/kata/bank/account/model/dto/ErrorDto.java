package com.kata.bank.account.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
	
	
	private Integer status;
	private LocalDateTime date;
	private String url;
	private String message;
	private List<String> errors;
	

}
