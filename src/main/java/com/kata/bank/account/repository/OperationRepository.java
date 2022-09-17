package com.kata.bank.account.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kata.bank.account.model.domain.Operation;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
	
	Page<Operation> findByAccount_AccountNumber(Long accountNumber, Pageable pageable);

}
