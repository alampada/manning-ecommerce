package com.ala.manningecommerce.service;

import com.ala.manningecommerce.controller.resources.SignupRequest;
import com.ala.manningecommerce.controller.resources.mappers.AccountEntityMapper;
import com.ala.manningecommerce.repository.AccountEntityRepository;
import com.ala.manningecommerce.repository.entities.AccountEntity;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

	private final AccountEntityRepository accountEntityRepository;

	private final AccountEntityMapper accountEntityMapper;

	public AccountEntity createAccount(SignupRequest signupRequest) {
		return accountEntityRepository.save(accountEntityMapper.map(signupRequest));
	}
}
