package com.ala.manningecommerce.repository;

import com.ala.manningecommerce.repository.entities.AccountEntity;

import org.springframework.data.repository.CrudRepository;

public interface AccountEntityRepository extends CrudRepository<AccountEntity, Long> {

	AccountEntity findByUsername(String username);
}
