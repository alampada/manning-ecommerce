package com.ala.manningecommerce.service;

import com.ala.manningecommerce.controller.resources.SignupRequest;
import com.ala.manningecommerce.controller.resources.mappers.AccountEntityMapper;
import com.ala.manningecommerce.repository.AccountEntityRepository;
import com.ala.manningecommerce.repository.entities.AccountEntity;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

	private final AccountEntityRepository accountEntityRepository;

	private final AccountEntityMapper accountEntityMapper;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public AccountEntity createAccount(SignupRequest signupRequest) {
		AccountEntity accountEntity = accountEntityMapper.map(signupRequest);
		accountEntity = accountEntity.toBuilder().password(bCryptPasswordEncoder.encode(accountEntity.getPassword()))
				.build();
		return accountEntityRepository.save(accountEntity);
	}
}
