package com.ala.manningecommerce.repository;

import org.springframework.data.repository.CrudRepository;

public interface PastryEntityRepository extends CrudRepository<PastryEntity, Long> {

	PastryEntity findByCode(String code);
}
