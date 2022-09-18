package com.kata.bank.account.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kata.bank.account.Application;
import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.model.domain.Client;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ClientRepository clientRepository;

	private static boolean initialized = false;

	@Before
	public void setUp() {
		if (!initialized) {
			System.out.println("--------------> AccountRepositoryTest <--------------");

			Client client = new Client(1234L, "said", "zbiri", "test@gmail.com");
			Account account = new Account(111L, client);

			clientRepository.save(client);
			accountRepository.save(account);
			initialized = true;
		}
	}

	@Test
	public void should_get_account_for_a_known_account_number() {
		assertTrue(accountRepository.findByAccountNumber(111L).isPresent());
	}

	@Test
	public void should_throw_error_for_an_unknown_account() {
		assertThat(accountRepository.findByAccountNumber(123456L), is(Optional.empty()));
	}

}
