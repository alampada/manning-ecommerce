package com.ala.manningecommerce.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Pastry {

	String code;

	String name;

	BigDecimal price;
}
