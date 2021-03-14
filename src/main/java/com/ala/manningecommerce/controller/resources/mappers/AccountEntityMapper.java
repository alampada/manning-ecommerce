package com.ala.manningecommerce.controller.resources.mappers;

import com.ala.manningecommerce.controller.resources.SignupRequest;
import com.ala.manningecommerce.repository.entities.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {

	AccountEntity map(SignupRequest signupRequest);
}
