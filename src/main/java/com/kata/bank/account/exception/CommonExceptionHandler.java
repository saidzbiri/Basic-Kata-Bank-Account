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
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kata.bank.account.model.dto.ErrorDto;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Object> handleResourceAccessException(ResourceAccessException ex, WebRequest request) {
        return new ResponseEntity<>(buildErrorDto(ex, request, NOT_FOUND), NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        return new ResponseEntity<>(buildErrorDto(ex, request, BAD_REQUEST), BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        log.error("handleMethodArgumentNotValid() - MethodArgumentNotValidException: {}", ex.getMessage());

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorDto errorDto = buildErrorDto(ex, request, BAD_REQUEST, errors);

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    private ErrorDto buildErrorDto(Exception ex, WebRequest request, HttpStatus status) {
        String url = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        return ErrorDto.builder()
                .status(status.value())
                .date(LocalDateTime.now())
                .url(url)
                .message(ex.getMessage())
                .build();
    }

    private ErrorDto buildErrorDto(Exception ex, WebRequest request, HttpStatus status, List<String> errors) {
        String url = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        return ErrorDto.builder()
                .status(status.value())
                .date(LocalDateTime.now())
                .url(url)
                .message(ex.getMessage())
                .errors(errors)
                .build();
    }

}
