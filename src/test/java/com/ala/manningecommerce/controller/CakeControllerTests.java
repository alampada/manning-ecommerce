package com.ala.manningecommerce.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ala.manningecommerce.domain.Pastry;
import com.ala.manningecommerce.service.CakeService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;


@WebMvcTest(CakeController.class)
class CakeControllerTests {

	@Autowired
	private WebClient webClient;

	@MockBean
	private CakeService cakeService;

	@Test
	public void shouldLoadHomePage() throws Exception {
		given(cakeService.getPastries()).willReturn(List.of(
				Pastry.builder().code("abcr").name("All Butter Croissant").price(new BigDecimal("0.75")).build(),
				Pastry.builder().code("ccr").name("Chocolate Croissant").price(new BigDecimal("0.95")).build()
				)
		);
		HtmlPage page = webClient.getPage("/");
		assertThat(page.getElementById("card-abcr").asText()).contains("All Butter Croissant");
		assertThat(page.getElementById("card-ccr").asText()).contains("Chocolate Croissant");

	}

}