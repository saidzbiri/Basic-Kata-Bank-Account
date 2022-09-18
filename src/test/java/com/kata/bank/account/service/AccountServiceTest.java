package com.kata.bank.account.service;

import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import com.kata.bank.account.exception.AccountNotFoundException;
import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.model.domain.Client;
import com.kata.bank.account.repository.AccountRepository;
import com.kata.bank.account.service.impl.AccountServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private AccountService accountService = new AccountServiceImpl();

	private static Long ACCOUNT_NUMBER = 333L;
	private Client client = new Client(123L, "said", "zbiri", "said@gmail.com");
	private Account account = new Account(333L, client);

	@Before
	public void setUp() {
		System.out.println("--------------> AccountServiceTest <--------------");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should_get_account_for_account_number() {
		when(accountRepository.findByAccountNumber(ACCOUNT_NUMBER)).thenReturn(Optional.of(account));
		assertThat(accountService.findByAccountNumber(ACCOUNT_NUMBER), is(Optional.of(account)));
	}

	@Test(expected = AccountNotFoundException.class)
	public void should_throw_account_not_found_when_trying_to_get_account_for_unknown_account_number()
			throws AccountNotFoundException {
		when(accountRepository.findByAccountNumber(ACCOUNT_NUMBER)).thenReturn(Optional.empty());
		accountService.findByAccountNumber(ACCOUNT_NUMBER)
				.orElseThrow(() -> new AccountNotFoundException(String.valueOf(ACCOUNT_NUMBER)));
	}

}
