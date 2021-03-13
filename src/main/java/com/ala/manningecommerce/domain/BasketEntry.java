package com.ala.manningecommerce.domain;

import com.ala.manningecommerce.repository.entities.PastryEntity;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BasketEntry {

	PastryEntity pastryEntity;

	int quantity;

}
