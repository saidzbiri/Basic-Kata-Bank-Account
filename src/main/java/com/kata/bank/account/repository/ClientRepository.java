package com.kata.bank.account.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kata.bank.account.model.domain.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	Optional<Client> findByUsernameOrEmail(String username, String email);
}
