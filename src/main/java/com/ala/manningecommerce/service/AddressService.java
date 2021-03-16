package com.ala.manningecommerce.service;

import com.ala.manningecommerce.controller.resources.SignupRequest;
import com.ala.manningecommerce.controller.resources.mappers.AddressEntityMapper;
import com.ala.manningecommerce.repository.AddressEntityRepository;
import com.ala.manningecommerce.repository.entities.AddressEntity;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

	private final AddressEntityRepository addressEntityRepository;

	private final AddressEntityMapper addressEntityMapper;

	public AddressEntity addAddress(SignupRequest signupRequest) {
		return addressEntityRepository.save(addressEntityMapper.map(signupRequest));
	}

	public AddressEntity getAddress(String email) {
		return addressEntityRepository.findByEmail(email);
	}
}
