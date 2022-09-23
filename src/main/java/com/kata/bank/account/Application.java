package com.kata.bank.account;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kata.bank.account.model.RoleName;
import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.model.domain.Client;
import com.kata.bank.account.service.AccountService;
import com.kata.bank.account.service.ClientService;

@SpringBootApplication
public class Application implements CommandLineRunner {

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

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode("2022");

		Client client = Client.builder().clientNumber(1L).firstname("said").lastname("Z'BIRI").username("szbiri")
				.email("test@gmail.com").password(encodedPassword).role(RoleName.ROLE_USER).build();

		Client newClient = clientService.saveClient(client);

		Account account = Account.builder().accountNumber(123L).dateCreation(new Date()).client(newClient).build();

		accountService.saveAccount(account);

	}

}
