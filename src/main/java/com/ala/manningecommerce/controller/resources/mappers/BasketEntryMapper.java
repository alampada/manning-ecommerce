package com.ala.manningecommerce.controller.resources.mappers;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.ala.manningecommerce.controller.resources.BasketEntryResource;
import com.ala.manningecommerce.domain.BasketEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BasketEntryMapper {

	@Mapping(target = "code", source = "pastryEntity.code")
	@Mapping(target = "name", source = "pastryEntity.name")
	@Mapping(target = "cost", source = ".", qualifiedByName = "basketEntityToCost")
	BasketEntryResource map(BasketEntry basketEntry);

	@Named("basketEntityToCost")
	default String basketEntityToCost(BasketEntry basketEntry) {
		DecimalFormat format = new DecimalFormat("#.00");
		BigDecimal result = basketEntry.getPastryEntity().getPrice().multiply(
				BigDecimal.valueOf(basketEntry.getQuantity()));
		return format.format(result);
	}
}
