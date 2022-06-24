package com.thinkbox.reactive.service;

import com.thinkbox.reactive.dto.QuoteDto;
import com.thinkbox.reactive.repository.QuoteRepository;
import com.thinkbox.reactive.utils.AppUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class QuoteService {

    @Autowired
    private QuoteRepository repository;


    public Flux<QuoteDto> getQuotes(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Flux<QuoteDto> getQuotesBySymbol(String symbol){
        return repository.findBySymbol(symbol).map(AppUtils::entityToDto);
    }
    
    public Mono<QuoteDto> getQuote(String id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<QuoteDto> saveQuote(Mono<QuoteDto> quoteDtoMono){
        log.info("service method called ...");
      return  quoteDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<QuoteDto> updateQuote(Mono<QuoteDto> quoteDtoMono,String id){
       return repository.findById(id)
                .flatMap(p->quoteDtoMono.map(AppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);

    }

    public Mono<Void> deleteQuote(String id){
        return repository.deleteById(id);
    }
}
