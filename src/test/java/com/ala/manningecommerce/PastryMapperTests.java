package com.ala.manningecommerce;

import java.math.BigDecimal;

import com.ala.manningecommerce.domain.Pastry;
import com.ala.manningecommerce.repository.PastryEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PastryMapperTests {
	private PastryMapper mapper = new PastryMapperImpl();

	@Test
	void testMap() {
		PastryEntity pastryEntity = new PastryEntity(1L, "aa", "bb", BigDecimal.ONE);

		Pastry expected = new Pastry("aa", "bb", BigDecimal.ONE);

		Pastry result = mapper.map(pastryEntity);

		assertThat(result).isEqualTo(expected);
	}
}