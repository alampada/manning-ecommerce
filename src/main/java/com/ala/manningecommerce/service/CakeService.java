package com.ala.manningecommerce.service;

import java.math.BigDecimal;
import java.util.List;

import com.ala.manningecommerce.domain.Pastry;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CakeService {

	private static List<Pastry> PASTRIES = List.of(
			Pastry.builder().code("abcr").name("All Butter Croissant").price(new BigDecimal("0.75")).build(),
			Pastry.builder().code("ccr").name("Chocolate Croissant").price(new BigDecimal("0.95")).build(),
			Pastry.builder().code("b").name("Fresh Baguette").price(new BigDecimal("1.60")).build(),
			Pastry.builder().code("rv").name("Red Velvet").price(new BigDecimal("3.95")).build(),
			Pastry.builder().code("vs").name("Victoria Sponge").price(new BigDecimal("5.45")).build(),
			Pastry.builder().code("cc").name("Carrot Cake").price(new BigDecimal("3.45")).build()
	);

	public List<Pastry> getPastries() {
		return PASTRIES;
	}
}
