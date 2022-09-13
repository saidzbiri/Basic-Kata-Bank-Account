package com.kata.bank.account.controller;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import org.springframework.http.MediaType;
import com.kata.bank.account.Application;
import com.kata.bank.account.mapper.Mapper;
import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.model.domain.Client;
import com.kata.bank.account.model.domain.Operation;
import com.kata.bank.account.model.dto.OperationCreationDto;
import com.kata.bank.account.model.dto.OperationDto;
import com.kata.bank.account.model.hateoas.OperationResourceAssembler;
import com.kata.bank.account.service.AccountService;
import com.kata.bank.account.service.OperationService;

import net.minidev.json.JSONValue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class OperationControllerTest {
	
	@MockBean
    private OperationService operationService;
	
	@MockBean
    private AccountService accountService;
	

    @Autowired
    private MockMvc mockMvc;
    
    
    private Client client = new Client(4545L, "said", "zbiri", "said@gmail.com");
    private static final Long ACCOUNT_NUMBER = 1001L;
    private static final String OPERATION_TYPE = "Deposit";
    
    @Test
    public void should_execute_operation() throws Exception {
    	Account account = new Account(ACCOUNT_NUMBER, 50L, client);
    	OperationCreationDto operationRequest = new OperationCreationDto(ACCOUNT_NUMBER, OPERATION_TYPE, 50d);
    	Operation expectedOperation = Operation.builder()
											   .operationType("deposit")
											   .amount(50d)
											   .date(new Date())
											   .account(account)
											   .build();

        when(accountService.findByAccountNumber(ACCOUNT_NUMBER))
                .thenReturn(account);
        
        when(operationService.save(operationRequest))
                .thenReturn(expectedOperation);

        String json = JSONValue.toJSONString(operationRequest); 
        
        this.mockMvc.perform(post("/operations/")
	                .content(json)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andDo(print())
	                .andExpect(status().isCreated());
    }
    
    @Test
    public void should_not_execute_operation_with_invalid_arguments() throws Exception {
        String json = "{\"accountNumber\":\"" + ACCOUNT_NUMBER + "\"}";
        this.mockMvc.perform(post("/operations/")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    
}
