package com.kata.bank.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.bank.account.model.domain.Client;
import com.kata.bank.account.repository.ClientRepository;
import com.kata.bank.account.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

}
