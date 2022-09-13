package com.kata.bank.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.model.domain.Client;
import com.kata.bank.account.service.AccountService;
import com.kata.bank.account.service.ClientService;


@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("App running ...");
		
		Client client = Client.builder()
							  .clientNumber(1L)
							  .firstname("said")
							  .lastname("Z'BIRI")
							  .email("zbirisaid95@gmail.com")
							  .build();
		
		Client newClient = clientService.saveClient(client);
		
		Account account = Account.builder()
								 .accountNumber(123L)
								 .dateCreation(new Date())
								 .client(newClient)
								 .build();

		accountService.saveAccount(account);
		
	}

}
