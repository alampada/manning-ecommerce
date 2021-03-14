package com.ala.manningecommerce.controller;

import javax.validation.Valid;

import com.ala.manningecommerce.controller.resources.SignupRequest;
import com.ala.manningecommerce.service.AccountService;
import com.ala.manningecommerce.service.AddressService;
import com.ala.manningecommerce.service.BasketService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
@Log4j2
public class AccountController {

	private final BasketService basketService;

	private final AccountService accountService;

	private final AddressService addressService;


	@GetMapping("/signup")
	public String renderSignup(Model model) {
		model.addAttribute("basketSize", basketService.getBasketSize());
		return "signup";
	}

	@PostMapping("/signup")
	@Transactional
	public String signup(Model model,
			@Valid SignupRequest signupRequest,
			Errors errors) {
		model.addAttribute("basketSize", basketService.getBasketSize());
		if (errors.hasErrors()) {
			model.addAttribute("errors", errors.getAllErrors());
			return "signup";
		}
		accountService.createAccount(signupRequest);
		addressService.addAddress(signupRequest);
		return "redirect:/";
	}


}
