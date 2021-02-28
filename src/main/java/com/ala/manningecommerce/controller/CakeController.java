package com.ala.manningecommerce.controller;

import java.util.List;

import com.ala.manningecommerce.domain.Pastry;
import com.ala.manningecommerce.service.CakeService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class CakeController {

	private final CakeService cakeService;

	@GetMapping
	public String homepage(Model model) {
		List<Pastry> pastryList = cakeService.getPastries();
		model.addAttribute("pastries", pastryList);
		return "index";
	}
}
