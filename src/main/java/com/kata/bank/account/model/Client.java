package com.kata.bank.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
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

	public Long getClientId() {
		return clientId;
	}

	public Long getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(Long clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
