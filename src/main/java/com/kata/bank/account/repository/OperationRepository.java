package com.kata.bank.account.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
	
	List<Operation> findByAccount(Account account, Pageable pageable);

}
