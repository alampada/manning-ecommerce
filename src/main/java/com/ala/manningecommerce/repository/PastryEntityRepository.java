package com.ala.manningecommerce.repository;

import com.ala.manningecommerce.repository.entities.PastryEntity;

import org.springframework.data.repository.CrudRepository;

public interface PastryEntityRepository extends CrudRepository<PastryEntity, Long> {

	PastryEntity findByCode(String code);
}
