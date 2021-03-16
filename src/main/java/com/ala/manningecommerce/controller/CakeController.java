package com.ala.manningecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import com.ala.manningecommerce.controller.resources.AddToBasketRequest;
import com.ala.manningecommerce.controller.resources.DeleteFromBasketRequest;
import com.ala.manningecommerce.controller.resources.Pastry;
import com.ala.manningecommerce.controller.resources.PlaceOrderRequest;
import com.ala.manningecommerce.repository.entities.AccountEntity;
import com.ala.manningecommerce.repository.entities.AddressEntity;
import com.ala.manningecommerce.service.AddressService;
import com.ala.manningecommerce.service.BasketService;
import com.ala.manningecommerce.service.CakeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
@Log4j2
public class CakeController {

	private final CakeService cakeService;

	private final BasketService basketService;

	private final AddressService addressService;

	@GetMapping
	public String homepage(Model model) {
		List<Pastry> pastryList = cakeService.getPastries();
		int basketSize = basketService.getBasketSize();
		model.addAttribute("pastries", pastryList);
		model.addAttribute("basketSize", basketSize);
		return "index";
	}

	@PostMapping("/basket")
	public String addToBasket(AddToBasketRequest addToBasketRequest) {
		log.info("adding to basket " + addToBasketRequest);
		basketService.addToBasket(addToBasketRequest.getCode());
		return "redirect:/";
	}

	@GetMapping("/basket")
	public String getBasket(Model model,
			@AuthenticationPrincipal AccountEntity accountEntity) {
		log.info("account entity : " + accountEntity);
		if (accountEntity != null) {
			AddressEntity address = addressService.getAddress(accountEntity.getUsername());
			model.addAttribute("address", address);
		}
		model.addAttribute("basketSize", basketService.getBasketSize());
		model.addAttribute("items", basketService.getContents());
		return "basket";
	}

	@PostMapping("/basket/delete")
	public String deleteFromBasket(DeleteFromBasketRequest deleteFromBasketRequest,
			Model model) {
		basketService.removeItem(deleteFromBasketRequest.getCode());
		model.addAttribute("basketSize", basketService.getBasketSize());
		model.addAttribute("items", basketService.getContents());
		return "redirect:/basket";
	}

	@PostMapping("/order")
	public String placeOrder(Model model,
			@Valid PlaceOrderRequest request,
			Errors errors) {
		if (errors.hasErrors()) {
			model.addAttribute("errors", errors.getAllErrors());
			model.addAttribute("basketSize", basketService.getBasketSize());
			model.addAttribute("items", basketService.getContents());
			return "basket";
		}
		log.info("placing order: " + request);
		basketService.clear();
		model.addAttribute("basketSize", basketService.getBasketSize());
		return "order";
	}
}
