package com.thinkbox.reactive.repository;

import com.thinkbox.reactive.entity.Quote;

import reactor.core.publisher.Flux;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends ReactiveMongoRepository<Quote,String> {
	
	@Query(value= "{symbol:{$eq:?0}}", sort="{date:-1}")
	Flux<Quote> findBySymbol(String symbol);

}
