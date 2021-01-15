package com.ala.manningecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CakeController {

	@GetMapping
	public String homepage() {
		return "index";
	}
}
