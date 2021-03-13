package com.ala.manningecommerce.controller.resources;

import lombok.Value;

@Value
public class PlaceOrderRequest {

	String addressLineOne;

	String addressLineTwo;

	String postCode;
}
