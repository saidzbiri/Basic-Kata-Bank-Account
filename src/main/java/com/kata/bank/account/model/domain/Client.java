package com.kata.bank.account.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Long clientId;

	@Column(name = "client_number", unique = true, nullable = false)
	private Long clientNumber;

	private String firstname;

	private String lastname;

	private String email;

	public Client() {
		super();
	}

	public Client(Long clientNumber, String firstname, String lastname, String email) {
		super();
		this.clientNumber = clientNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

}
