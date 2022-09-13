package com.kata.bank.account.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kata.bank.account.model.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	
	Optional<Account> findByAccountNumber(Long accountNumber);

}
