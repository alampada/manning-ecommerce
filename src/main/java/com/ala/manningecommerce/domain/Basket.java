package com.ala.manningecommerce.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ala.manningecommerce.repository.entities.PastryEntity;

public class Basket {

	private final Map<PastryEntity, Integer> contents;

	public Basket() {
		contents = new HashMap<>();
	}

	public void addItem(PastryEntity pastryEntity) {
		contents.put(pastryEntity, contents.getOrDefault(pastryEntity, 0) + 1);
	}

	public List<BasketEntry> getContents() {
		return contents.entrySet().stream()
				.map(e -> BasketEntry.builder().pastryEntity(e.getKey()).quantity(e.getValue()).build())
				.collect(Collectors.toList());
	}

	public int getSize() {
		return contents.values().stream().reduce(0, Integer::sum);
	}

	public void removeItem(PastryEntity pastryEntity) {
		Optional.ofNullable(contents.get(pastryEntity)).orElseThrow();
		contents.compute(pastryEntity, (k, v) -> (v == 1) ? null : v - 1);
	}
}
