package com.ala.manningecommerce.controller.resources;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class SignupRequest {

	@Email
	String email;

	@NotBlank
	String password;

	@NotBlank
	String addressLineOne;

	@NotBlank
	String addressLineTwo;

	@NotBlank
	String postCode;
}
