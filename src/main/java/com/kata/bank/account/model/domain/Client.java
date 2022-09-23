package com.kata.bank.account.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kata.bank.account.model.RoleName;

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

	private String username;

	private String lastname;

	private String email;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RoleName role;

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

	public Client(String firstname, String username, Long clientNumber, String email, String password, RoleName role) {
		this.firstname = firstname;
		this.username = username;
		this.clientNumber = clientNumber;
		this.email = email;
		this.password = password;
		this.role = role;
	}

}
