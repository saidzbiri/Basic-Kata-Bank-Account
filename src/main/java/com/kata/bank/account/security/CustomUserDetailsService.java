package com.kata.bank.account.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kata.bank.account.model.domain.Client;
import com.kata.bank.account.repository.ClientRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) {

		// Let people login with either username or email
		Client client = clientRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));

		return UserPrincipal.create(client);
	}

	// This method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long id) {

		Client user = clientRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));

		return UserPrincipal.create(user);
	}
}
