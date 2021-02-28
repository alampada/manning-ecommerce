package com.ala.manningecommerce.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PastryEntityRepositoryTests {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private PastryEntityRepository testObject;

	@Test
	void testFindAll() {

		PastryEntity pastry1 = new PastryEntity(null, "code1", "name1", new BigDecimal("10.0"));
		testEntityManager.persist(pastry1);

		PastryEntity pastry2 = new PastryEntity(null, "code2", "name2", new BigDecimal("20.0"));
		testEntityManager.persist(pastry2);

		Collection<PastryEntity> result = IterableUtil.toCollection(testObject.findAll());

		assertThat(result).containsAll(List.of(pastry1, pastry2));

	}

}