package com.ala.manningecommerce.controller.resources.mappers;

import com.ala.manningecommerce.controller.resources.SignupRequest;
import com.ala.manningecommerce.repository.entities.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressEntityMapper {

	AddressEntity map(SignupRequest signupRequest);
}
