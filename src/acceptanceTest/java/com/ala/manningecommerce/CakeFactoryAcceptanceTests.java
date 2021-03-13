package com.ala.manningecommerce;

import java.io.IOException;
import java.util.List;

import com.ala.manningecommerce.support.PostgresqlServerExtension;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, PostgresqlServerExtension.class})
@ActiveProfiles("acceptance")
public class CakeFactoryAcceptanceTests {


	@LocalServerPort
	private int serverPort;

	private String baseUrl;

	private WebClient webClient = new WebClient();

	@BeforeEach
	private void setup() {
		baseUrl = "http://localhost:" + serverPort;
	}

	@Test
	public void testAddToBasket() throws IOException {
		// first get page
		HtmlPage landingPage = webClient.getPage(baseUrl);

		HtmlForm form = landingPage.getFormByName("form-abcr");
		DomElement basket = landingPage.getElementById("navbarResponsive");
		assertThat(form).isNotNull();
		assertThat(basket).isNotNull();
		assertThat(basket.asText()).contains("Basket (0 Items)");

		HtmlInput submitButton = form.getInputByName("submit-button");

		HtmlPage resultingPage = submitButton.click();

		assertThat(resultingPage.getElementById("navbarResponsive").asText()).contains("Basket (1 Items)");
	}

	@Test
	public void testDeleteFromBasket() throws IOException {

		List<String> items = List.of("abcr", "ccr");

		for (String item : items) {
			HtmlPage landingPage = webClient.getPage(baseUrl);

			HtmlForm addForm = landingPage.getFormByName("form-" + item);

			HtmlInput submitButton = addForm.getInputByName("submit-button");

			submitButton.click();
		}

		HtmlPage basketPage = webClient.getPage(baseUrl + "/basket");

		HtmlForm form = basketPage.getFormByName("form-abcr");

		HtmlInput deleteButton = form.getInputByName("submit-button");

		HtmlPage resultingPage = deleteButton.click();

		assertThat(resultingPage.getElementById("form-abcr")).isNull();

		assertThat(resultingPage.getElementById("form-ccr")).isNotNull();

		DomElement basket = resultingPage.getElementById("navbarResponsive");
		assertThat(basket.asText()).contains("Basket (1 Items)");
	}
}

