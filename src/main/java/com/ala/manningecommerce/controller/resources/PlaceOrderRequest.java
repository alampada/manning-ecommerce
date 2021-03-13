package com.ala.manningecommerce.controller.resources;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Value;

@Value
public class PlaceOrderRequest {

	@Size(min = 3, max = 20)
	String addressLineOne;

	@Size(min = 3, max = 20)
	String addressLineTwo;

	@Pattern(regexp = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$", message = "must be a valid UK postcode")
	String postCode;
}
