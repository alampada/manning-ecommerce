package com.ala.manningecommerce.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Pastry {

	Integer id;

	String code;

	String name;

	BigDecimal price;
}
