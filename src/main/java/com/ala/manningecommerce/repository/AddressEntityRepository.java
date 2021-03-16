package com.ala.manningecommerce.repository;

import com.ala.manningecommerce.repository.entities.AddressEntity;

import org.springframework.data.repository.CrudRepository;

public interface AddressEntityRepository extends CrudRepository<AddressEntity, Long> {
	AddressEntity findByEmail(String email);
}
