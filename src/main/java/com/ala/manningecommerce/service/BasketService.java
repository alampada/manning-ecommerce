package com.ala.manningecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ala.manningecommerce.controller.resources.BasketEntryResource;
import com.ala.manningecommerce.controller.resources.mappers.BasketEntryMapper;
import com.ala.manningecommerce.domain.Basket;
import com.ala.manningecommerce.repository.PastryEntityRepository;
import com.ala.manningecommerce.repository.entities.PastryEntity;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BasketService {

	private final PastryEntityRepository pastryEntityRepository;

	private final Basket basket;

	private final BasketEntryMapper basketEntryMapper;

	public void addToBasket(String pastryCode) {
		PastryEntity pastryEntity = Optional.ofNullable(pastryEntityRepository.findByCode(pastryCode)).orElseThrow();
		basket.addItem(pastryEntity);
	}

	public int getBasketSize() {
		return basket.getSize();
	}

	public List<BasketEntryResource> getContents() {
		return basket.getContents().stream().map(basketEntryMapper::map).collect(Collectors.toList());
	}

	public void removeItem(String code) {
		PastryEntity pastryEntity = Optional.ofNullable(pastryEntityRepository.findByCode(code)).orElseThrow();
		basket.removeItem(pastryEntity);
	}

	public void clear() {
		basket.clear();
	}
}
