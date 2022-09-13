package com.kata.bank.account.controller;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kata.bank.account.mapper.Mapper;
import com.kata.bank.account.model.domain.Operation;
import com.kata.bank.account.model.dto.OperationCreationDto;
import com.kata.bank.account.model.dto.OperationDto;
import com.kata.bank.account.model.hateoas.OperationResource;
import com.kata.bank.account.model.hateoas.OperationResourceAssembler;
import com.kata.bank.account.service.OperationService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Validated
@RestController
@RequestMapping("/operations")
public class OperationController {
	
	@Autowired
	private OperationService operationService;
			
	@Autowired
	private Mapper mapper;
	
	@Autowired
	OperationResourceAssembler assembler;
	
	@ApiOperation("create new operation")
	@PostMapping
	public ResponseEntity<OperationDto> executeOperation(@RequestBody @Valid OperationCreationDto operationRequest) {
		log.info("executeOperation() operationRequest: {}", operationRequest);
		
		OperationDto operationDto = mapper.toOperationDto(operationService.save(operationRequest));
		final URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").build().expand(operationDto.getId()).toUri();
		
		return ResponseEntity.created(location).body(operationDto);
	}
	
	@ApiOperation("search all operations of an account")
    @GetMapping
    public ResponseEntity<PagedResources<OperationResource>> getAllOperationForAClient(
                                                    @RequestParam(value = "accountNumber", required = true) Long accountNumber,
                                                    @PageableDefault(page = 0, size = 10, sort = "date", direction = Direction.DESC) Pageable pageable,
                                                    PagedResourcesAssembler<OperationDto> pagedAssembler
    												) {
		
		log.info("getAllOperationForAClient() accountNumber: {}", accountNumber);
		    	
    	Page<Operation> operationsHistory = operationService.findAllOperationsForAClient(accountNumber, pageable);
    	Page<OperationDto> operationsDto = operationsHistory.map(mapper::toOperationDto);	
    	    	 
    	PagedResources<OperationResource>  resources = pagedAssembler.toResource(operationsDto, assembler);
                  
        return ResponseEntity.ok().body(resources);
    }

	@ApiOperation("returns details of an operation")
    @GetMapping("/{operationId}")
    public ResponseEntity<OperationResource> getOperationInfo(@PathVariable("operationId") final int operationId) {
    	log.info("getOperationInfo() operationId: {}", operationId);
    	// Not implemented yet
        return null;
    }
	

}
