package com.ala.manningecommerce.service;

import com.ala.manningecommerce.Basket;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BasketService {

	private final Basket basket;

	public void addToBasket(String pastryCode) {
		basket.addItem();
	}

	public int getBasketSize() {
		return basket.getSize();
	}
}
