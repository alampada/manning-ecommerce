package com.ala.manningecommerce;

import com.ala.manningecommerce.domain.Pastry;
import com.ala.manningecommerce.repository.PastryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PastryMapper {

	Pastry map(PastryEntity pastryEntity);
}
