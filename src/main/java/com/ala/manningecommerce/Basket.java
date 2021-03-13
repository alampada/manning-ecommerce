package com.ala.manningecommerce;

import lombok.Data;

@Data
public class Basket {

	private int size;

	public void addItem() {
		size++;
	}
}
