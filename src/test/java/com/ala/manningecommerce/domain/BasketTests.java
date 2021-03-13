package com.ala.manningecommerce.domain;

import java.math.BigDecimal;
import java.util.List;

import com.ala.manningecommerce.repository.entities.PastryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTests {

	private Basket basket;

	@BeforeEach
	public void setup() {
		basket = new Basket();
	}

	@Test
	public void shouldAddOneElement() {
		PastryEntity pastryEntity = PastryEntity.builder().code("abcr").name("All Butter Croissant")
				.price(new BigDecimal("0.75")).build();

		basket.addItem(pastryEntity);

		assertThat(basket.getSize()).isEqualTo(1);
		assertThat(basket.getContents()).containsExactlyElementsOf(
				List.of(BasketEntry.builder().quantity(1).pastryEntity(pastryEntity).build()));
	}

	@Test
	public void shouldAddMoreThanOneInstance() {
		PastryEntity pastryEntity = PastryEntity.builder().code("abcr").name("All Butter Croissant")
				.price(new BigDecimal("0.75")).build();

		basket.addItem(pastryEntity);
		basket.addItem(pastryEntity);

		assertThat(basket.getSize()).isEqualTo(2);
		assertThat(basket.getContents()).containsExactlyElementsOf(
				List.of(BasketEntry.builder().quantity(2).pastryEntity(pastryEntity).build()));
	}

	@Test
	public void shouldAddMoreThanOneElement() {
		PastryEntity butterCroissant = PastryEntity.builder().code("abcr").name("All Butter Croissant")
				.price(new BigDecimal("0.75")).build();

		PastryEntity chocolateCroissant = PastryEntity.builder().code("ccr").name("Chocolate Croissant")
				.price(new BigDecimal("0.95")).build();

		basket.addItem(butterCroissant);
		basket.addItem(butterCroissant);
		basket.addItem(chocolateCroissant);

		assertThat(basket.getSize()).isEqualTo(3);
		assertThat(basket.getContents()).containsExactlyElementsOf(
				List.of(
						BasketEntry.builder().quantity(2).pastryEntity(butterCroissant).build(),
						BasketEntry.builder().quantity(1).pastryEntity(chocolateCroissant).build()));
	}

	@Test
	public void shouldReduceQuantityByOne() {
		PastryEntity butterCroissant = PastryEntity.builder().code("abcr").name("All Butter Croissant")
				.price(new BigDecimal("0.75")).build();

		PastryEntity chocolateCroissant = PastryEntity.builder().code("ccr").name("Chocolate Croissant")
				.price(new BigDecimal("0.95")).build();

		basket.addItem(butterCroissant);
		basket.addItem(butterCroissant);
		basket.addItem(chocolateCroissant);

		basket.removeItem(butterCroissant);

		assertThat(basket.getSize()).isEqualTo(2);
		assertThat(basket.getContents()).containsExactlyElementsOf(List.of(
				BasketEntry.builder().quantity(1).pastryEntity(butterCroissant).build(),
				BasketEntry.builder().quantity(1).pastryEntity(chocolateCroissant).build())
		);
	}

	@Test
	public void shouldRemoveLastOccurence() {
		PastryEntity butterCroissant = PastryEntity.builder().code("abcr").name("All Butter Croissant")
				.price(new BigDecimal("0.75")).build();

		PastryEntity chocolateCroissant = PastryEntity.builder().code("ccr").name("Chocolate Croissant")
				.price(new BigDecimal("0.95")).build();

		basket.addItem(butterCroissant);
		basket.addItem(chocolateCroissant);

		basket.removeItem(chocolateCroissant);

		assertThat(basket.getSize()).isEqualTo(1);
		assertThat(basket.getContents()).containsExactlyElementsOf(List.of(
				BasketEntry.builder().quantity(1).pastryEntity(butterCroissant).build())
		);
	}

}