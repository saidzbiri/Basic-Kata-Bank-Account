package com.kata.bank.account.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Client;
import com.kata.bank.account.repository.AccountRepository;
import com.kata.bank.account.service.impl.AccountServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
	
	@Mock
	private AccountRepository accountRepository;
	
	@InjectMocks
	private AccountService accountService = new AccountServiceImpl();
	
	private Client client = new Client(000L, "test", "test", "test@gmail.com");
	
	private Account account = new Account(333L, client);
	
	@Before
    public void setUp() {
		System.out.println("--------------> AccountServiceTest <--------------");
		MockitoAnnotations.initMocks(this);
        when(accountRepository.getAccountByAccountNumber(333L)).thenReturn(Optional.of(account));
    }
	
	@Test
	public void testGetAccount() {
		Account expectedAccount = accountService.getAccount(333L).get();
		assertEquals(expectedAccount.getAccountNumber(), account.getAccountNumber());
	}

}
