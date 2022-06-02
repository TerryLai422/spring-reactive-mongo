package com.javatechie.reactive;

import com.thinkbox.reactive.controller.QuoteController;
import com.thinkbox.reactive.dto.ProductDto;
import com.thinkbox.reactive.dto.QuoteDto;
import com.thinkbox.reactive.service.QuoteService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(QuoteController.class)
class SpringReactiveMongoApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	@MockBean
	private QuoteService service;

	@Test
	public void addQuoteTest() {
		Mono<QuoteDto> productDtoMono = Mono
				.just(new QuoteDto("AMD-20220531", "AMD", "20220531", 102.13, 103.57, 99.55, 101.22, 99125703L));
		when(service.saveQuote(productDtoMono)).thenReturn(productDtoMono);

		webTestClient.post().uri("/quotes").body(Mono.just(productDtoMono), ProductDto.class).exchange()
				.expectStatus().isOk();// 200

	}

	@Test
	public void getQuotesTest() {
		Flux<QuoteDto> quoteDtoFlux = Flux.just(new QuoteDto("AMD-20220531", "AMD", "20220531", 102.13, 103.57, 99.55, 101.22, 99125703L),
				new QuoteDto("NVDA-20220531", "NVDA", "20220531", 187.24,190.53,181.22, 183.2, 53808925L));
		when(service.getQuotes()).thenReturn(quoteDtoFlux);

		Flux<ProductDto> responseBody = webTestClient.get().uri("/products").exchange().expectStatus().isOk()
				.returnResult(ProductDto.class).getResponseBody();

		StepVerifier.create(responseBody).expectSubscription().expectNext(new ProductDto("102", "mobile", 1, 10000))
				.expectNext(new ProductDto("103", "TV", 1, 50000)).verifyComplete();

	}

	@Test
	public void getQuoteTest() {
		Mono<QuoteDto> productDtoMono = Mono.just(new QuoteDto("AMD-20220531", "AMD", "20220531", 102.13, 103.57, 99.55, 101.22, 99125703L));
		when(service.getQuote(any())).thenReturn(productDtoMono);

		Flux<ProductDto> responseBody = webTestClient.get().uri("/quotes/AMD-20220531").exchange().expectStatus().isOk()
				.returnResult(ProductDto.class).getResponseBody();

		StepVerifier.create(responseBody).expectSubscription().expectNextMatches(p -> p.getName().equals("mobile"))
				.verifyComplete();
	}

	@Test
	public void updateQuoteTest() {
		Mono<QuoteDto> quoteDtoMono = Mono.just(new QuoteDto("AMD-20220531", "AMD", "20220531", 102.13, 103.57, 99.55, 101.22, 99125703L));
		when(service.updateQuote(quoteDtoMono, "AMD-20220531")).thenReturn(quoteDtoMono);

		webTestClient.put().uri("/quotes/update/AMD-20220531").body(Mono.just(quoteDtoMono), QuoteDto.class).exchange()
				.expectStatus().isOk();// 200
	}

	@Test
	public void deleteQuoteTest() {
		given(service.deleteQuote(any())).willReturn(Mono.empty());
		webTestClient.delete().uri("/quotes/delete/AMD-20220531").exchange().expectStatus().isOk();// 200
	}

}
