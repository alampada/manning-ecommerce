package com.ala.manningecommerce.controller.resources.mappers;

import com.ala.manningecommerce.controller.resources.SignupRequest;
import com.ala.manningecommerce.repository.entities.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {

	@Mapping(source = "email", target = "username")
	AccountEntity map(SignupRequest signupRequest);
}
