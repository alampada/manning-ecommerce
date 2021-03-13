package com.ala.manningecommerce.controller.resources.mappers;

import java.math.BigDecimal;

import com.ala.manningecommerce.controller.resources.BasketEntryResource;
import com.ala.manningecommerce.domain.BasketEntry;
import com.ala.manningecommerce.repository.entities.PastryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BasketEntryMapperTests {

	private BasketEntryMapper basketEntryMapper;

	@BeforeEach
	public void setup() {
		basketEntryMapper = new BasketEntryMapperImpl();
	}

	@Test
	public void convertsEntityToResource() {
		BasketEntry basketEntry = BasketEntry.builder()
				.pastryEntity(PastryEntity.builder().code("abcr").name("All Butter Croissant")
						.price(new BigDecimal("0.75")).build())
				.quantity(2).build();

		BasketEntryResource expected = BasketEntryResource.builder()
				.code("abcr")
				.name("All Butter Croissant")
				.cost("1.50")
				.quantity(2)
				.build();

		BasketEntryResource basketEntryResource = basketEntryMapper.map(basketEntry);

		assertThat(basketEntryResource).isEqualTo(expected);
	}

}