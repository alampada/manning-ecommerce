package com.ala.manningecommerce.controller.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class BasketEntryResource {

	String code;

	String name;

	int quantity;

	String cost;
}
