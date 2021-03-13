package com.ala.manningecommerce;

import java.io.IOException;

import com.ala.manningecommerce.support.PostgresqlServerExtension;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
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
public class AddToBasketAcceptanceTest {


	@LocalServerPort
	private int serverPort;

	private String baseUrl;

	private WebClient webClient;

	@BeforeEach
	private void setup() {
		baseUrl = "http://localhost:" + serverPort;
		webClient = new WebClient();
	}

	@Test
	public void addToBasket() throws IOException {
		// first get page
		HtmlPage landingPage = webClient.getPage(baseUrl);

		HtmlForm form = landingPage.getFormByName("form-abcr");
		DomElement basket = landingPage.getElementById("basket");
		assertThat(form).isNotNull();
		assertThat(basket).isNotNull();
		assertThat(basket.asText()).contains("Basket (0 Items)");

		HtmlInput submitButton = form.getInputByName("submit-button");

		HtmlPage resultingPage = submitButton.click();

		assertThat(resultingPage.getElementById("basket").asText()).contains("Basket (1 Items)");
	}
}

