package com.kata.bank.account.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kata.bank.account.model.dto.ErrorDto;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest request) {
		
		ErrorDto errorDto = buildErrorDto(ex, request, HttpStatus.NOT_FOUND.value(), null);       
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(OperationTypeNotSupportedException.class)
	public ResponseEntity<Object> handleOperationTypeNotSupportedException(OperationTypeNotSupportedException ex, WebRequest request) {
		
		ErrorDto errorDto = buildErrorDto(ex, request, HttpStatus.BAD_REQUEST.value(), null);					
		return ResponseEntity.badRequest().body(errorDto);
	}
	
	
	@ExceptionHandler(InsufficientFundException.class)
	public ResponseEntity<Object> handleInsufficientFundException(InsufficientFundException ex, WebRequest request) {
		
		ErrorDto errorDto = buildErrorDto(ex, request, HttpStatus.NOT_FOUND.value(), null);	
		return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
	}
	

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleMethodArgumentNotValid() - MethodArgumentNotValidException: {}", ex.getMessage());

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorDto errorDto = buildErrorDto(ex, request, HttpStatus.BAD_REQUEST.value(), errors);
        
        		
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
    

	private ErrorDto buildErrorDto(Exception ex, WebRequest request, int code, List<String> errors) {
		List<String> erreurs = (errors == null) ? new ArrayList<String>() : errors;
		
		String url = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
		return ErrorDto.builder()
				.status(code)
				.date(LocalDateTime.now())
				.url(url)
				.message(ex.getMessage())
				.errors(erreurs)
				.build();
	}

}
