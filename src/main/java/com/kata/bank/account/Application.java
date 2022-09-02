package com.kata.bank.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Client;
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
		
		Client client = clientService.saveClient(new Client(1L, "Said", "Z'BIRI", "zbirisaid95@gmail.com"));	 
		accountService.updateAccount(new Account(123L, client));
		
	}

}
