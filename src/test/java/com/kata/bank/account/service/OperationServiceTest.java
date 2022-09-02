package com.kata.bank.account.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Client;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.repository.OperationRepository;
import com.kata.bank.account.service.impl.OperationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceTest {
	
	@Mock
	private OperationRepository operationRepository;
	
	@InjectMocks
	private OperationService operationService = new OperationServiceImpl();
	
	private Client client = new Client(4545L, "test", "test", "test@gmail.com");
    private Account account = new Account(120L, client);
    
    @Before
    public void setUp() {
		System.out.println("--------------> OperationServiceTest <--------------");
    }
	
	@Test
	public void testRegisterOperation() {
		Operation operation = new Operation("withdrawal", 200d, account);
		
		when(operationRepository.save(any())).thenReturn(operation);	
        Operation insertedOperation = operationService.registerOperation(operation);
        
        assertThat(insertedOperation, is(operation));

	}

}
