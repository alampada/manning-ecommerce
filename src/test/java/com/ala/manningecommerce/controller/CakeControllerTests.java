package com.ala.manningecommerce.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ala.manningecommerce.controller.resources.BasketEntryResource;
import com.ala.manningecommerce.controller.resources.Pastry;
import com.ala.manningecommerce.service.AddressService;
import com.ala.manningecommerce.service.BasketService;
import com.ala.manningecommerce.service.CakeService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
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

	@MockBean
	private AddressService addressService;

	@MockBean
	private BasketService basketService;

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

	@Test
	public void shouldLoadBasketPage() throws IOException {
		given(basketService.getContents()).willReturn(List.of(
				BasketEntryResource.builder()
						.name("All Butter Croissant")
						.code("abcr")
						.cost("1.50")
						.quantity(2)
						.build(),
				BasketEntryResource.builder()
						.name("Chocolate Croissant")
						.code("ccr")
						.cost("0.95")
						.quantity(1)
						.build()
		));
		HtmlPage page = webClient.getPage("/basket");

		HtmlTable table = (HtmlTable) page.getElementById("basket-table");

		assertThat(table.getBodies().get(0).getRows().get(0).getCell(0).asText()).isEqualTo("All Butter Croissant");
		assertThat(table.getBodies().get(0).getRows().get(0).getCell(1).asText()).isEqualTo("2");
		assertThat(table.getBodies().get(0).getRows().get(0).getCell(2).asText()).isEqualTo("1.50");

		assertThat(table.getBodies().get(0).getRows().get(1).getCell(0).asText()).isEqualTo("Chocolate Croissant");
		assertThat(table.getBodies().get(0).getRows().get(1).getCell(1).asText()).isEqualTo("1");
		assertThat(table.getBodies().get(0).getRows().get(1).getCell(2).asText()).isEqualTo("0.95");
	}

	@Test
	public void shouldPlaceOrder() throws IOException {
		HtmlPage page = webClient.getPage("/basket");

		HtmlForm form = page.getFormByName("form-order");

		Map<String, String> inputs = Map.of(
				"addressLineOne", "line one",
				"addressLineTwo", "line two",
				"postCode", "AA1 1AA"
		);

		inputs.forEach((k, v) -> {
			try {
				form.getInputByName(k).type(v);
			}
			catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});

		HtmlInput submitButton = form.getInputByName("submit-button");

		HtmlPage resultingPage = submitButton.click();

		assertThat(resultingPage.getElementById("message").asText()).contains("Thank you");
	}

}