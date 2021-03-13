package com.ala.manningecommerce.controller.resources.mappers;

import com.ala.manningecommerce.controller.resources.Pastry;
import com.ala.manningecommerce.repository.entities.PastryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PastryMapper {

	Pastry map(PastryEntity pastryEntity);
}
