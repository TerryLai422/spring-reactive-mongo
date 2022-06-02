package com.thinkbox.reactive.repository;

import com.thinkbox.reactive.entity.Quote;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends ReactiveMongoRepository<Quote,String> {
}
